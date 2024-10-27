import { cloneDeep, flatten, forOwn, isFunction, isObject, isString } from 'lodash';
import Ajv, {
    AnySchema,
    AsyncValidateFunction,
    JSONSchemaType,
    KeywordDefinition,
    Format,
    Options as AjvOptions,
    ValidateFunction, ValidationError,
} from 'ajv';
import addFormats from 'ajv-formats';
import addKeywords from 'ajv-keywords';
import addErrors from 'ajv-errors';
import { AnyValidateFunction } from 'ajv/dist/types';
import { JsonValidatorOptions } from './json-validator-options';
import { ApplicationException } from '@lib/exceptions/application-exception';
import { ajvErrorAdapter } from '@lib/exceptions/error-adapters/ajv-error-adapter';

export const defaultAjvOptions: AjvOptions = {
    $data: true,
    addUsedSchema: false, // Avoid caching by default
    allErrors: true,
    coerceTypes: true,
    passContext: true,
    strict: false,
};

export const defaultAjvKeywords = [
    'transform',
];

type MaybeAsync = {
    $async?: boolean;
};

export type SchemaDictionary =  { [schemaName: string]: JSONSchemaType<any> | AnySchema };

export class JsonValidator {
    private readonly ajv: Ajv;
    private readonly validators: { [schemaName: string]: AnyValidateFunction };

    constructor(options: JsonValidatorOptions = {}) {
        const ajvOptions = Object.assign({}, defaultAjvOptions, options.ajvOptions);
        this.ajv = new Ajv(ajvOptions);

        addKeywords(this.ajv, [
            ...defaultAjvKeywords,
            ...(Array.isArray(options.ajvKeywords) ? options.ajvKeywords : []),
        ]);
        addFormats(this.ajv, options.ajvFormatOptions);
        addErrors(this.ajv,  options.ajvErrorOptions);

        this.validators = {};
    }

    addCustomFormats(customFormats: [string, Format][]) {
        customFormats.forEach(([keywordName, formatDefinition]) =>
            this.ajv.addFormat(keywordName, formatDefinition));
    }

    addCustomKeywords(...customKeywords: KeywordDefinition[] | KeywordDefinition[][]): void {
        flatten(customKeywords).forEach(keyword => this.ajv.addKeyword(keyword));
    }

    compile(jsonSchema: JSONSchemaType<any> | AnySchema): AnyValidateFunction {
        // AJV many types for json schema are conflicting a bit here
        return this.ajv.compile(jsonSchema as any);
    }

    getValidator(schemaName: string): AnyValidateFunction {
        return this.validators[schemaName];
    }

    registerSchema(schemaName: string, jsonSchema: JSONSchemaType<any> | AnySchema): void {
        this.validators[schemaName] = this.compile(jsonSchema);
    }

    registerSchemas(schemas: SchemaDictionary): void {
        if (!isObject(schemas)) {
            throw ApplicationException.invalidRequest('Schemas should be an object');
        }
        forOwn(schemas, (schema, schemaName) => {
            this.registerSchema(schemaName, schema);
        });
    }

    validate<T>(
        schema: string | JSONSchemaType<T> | AnySchema | AnyValidateFunction,
        data: T,
        contextParams?: any
    ): Promise<T> {
        const validator = this.getSchemaValidator(schema);
        const clonedData = cloneDeep(data);
        // eslint-disable-next-line @typescript-eslint/no-unsafe-assignment
        const context = Object.assign({}, this.ajv, contextParams);

        return this.runValidator(validator, context, clonedData);
    }

    async validateAsync<T>(validator: AsyncValidateFunction, context: any, data: T): Promise<T> {
        try {
            await validator.bind(context)(data);
            return data;
        } catch (error) {
            throw ajvErrorAdapter((error as ValidationError).errors);
        }
    }

    validateSync<T>(validator: ValidateFunction, context: any, data: T): T {
        const valid = validator.call(context, data);
        if (!valid) {
            // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
            throw ajvErrorAdapter(validator.errors!);
        }
        return data;
    }

    private getSchemaValidator(
        schema: string | JSONSchemaType<any> | AnySchema | ValidateFunction,
    ): AnyValidateFunction {
        let validator;

        if (isString(schema)) {
            validator = this.getValidator(schema);

            if (!validator) {
                throw ApplicationException.invalidRequest(
                    `Schema with name "${schema}" not found. Make sure to call "registerSchema" first`
                );
            }
        } else if (isFunction(schema)) {
            validator = schema;
        } else {
            validator = this.compile(schema);
        }

        return validator;
    }

    private runValidator<T>(validator: AnyValidateFunction, context: any, data: T): Promise<T> {
        if ((validator as MaybeAsync).$async) {
            return this.validateAsync(validator as AsyncValidateFunction, context, data);
        }
        try {
            const result = this.validateSync(validator, context, data);
            return Promise.resolve(result);
        } catch (error) {
            return Promise.reject(error);
        }
    }
}
