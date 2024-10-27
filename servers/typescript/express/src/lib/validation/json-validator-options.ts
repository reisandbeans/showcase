import { Options as AjvOptions } from 'ajv';
import { ErrorMessageOptions } from 'ajv-errors';
import { FormatsPluginOptions } from 'ajv-formats';
import { ApplicationError } from '@lib/exceptions/application-exception';

export interface JsonValidatorOptions<T = ApplicationError> {
    ajvErrorOptions?: ErrorMessageOptions;
    ajvFormatOptions?: FormatsPluginOptions;
    ajvKeywords?: string[];
    ajvOptions?: AjvOptions;
}
