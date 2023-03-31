import { JsonValidator } from '@lib/validation/json-validator';
import { JsonValidatorOptions } from '@lib/validation/json-validator-options';
import { NextFunction, Request, Response } from 'express';
import { AnySchema, JSONSchemaType } from 'ajv';

export function createValidator(options?: JsonValidatorOptions) {
    const jsonValidator = new JsonValidator(options);

    return (schema: JSONSchemaType<any> | AnySchema) => {
        const validationFunction = jsonValidator.compile(schema);
        return (req: Request, res: Response, next: NextFunction) => {
            jsonValidator.validate(validationFunction, req)
                .then(() => {
                    next();
                })
                .catch((error) => {
                    next(error);
                });
        };
    };
}
