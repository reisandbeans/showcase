import { ErrorObject } from 'ajv';
import { ApplicationException, ErrorDetail } from '@lib/exceptions/application-exception';
import { invalidParameters } from '@lib/exceptions/exception-factory';
import { ErrorName } from '@lib/exceptions/error-name';

const keywordMap: { [key: string]: ErrorName | undefined } = {
    required: ErrorName.MissingRequiredParameter,
};

export function ajvErrorAdapter(errors: Partial<ErrorObject>[]): ApplicationException {
    const details: ErrorDetail[] = errors.map(mapError);
    return invalidParameters(details);
}

function mapError(error: Partial<ErrorObject>): ErrorDetail {
    // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
    const name = keywordMap[error.keyword!] || ErrorName.MissingRequiredParameter;
    // TODO add better error mapping here
    let { message } = error;
    // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
    const fieldName = (error.instancePath!).replace('/', '');
    if (error.keyword === 'enum') {
        // eslint-disable-next-line @typescript-eslint/no-unsafe-assignment
        const allowedValues = error?.params?.['allowedValues'];
        message += `: ${allowedValues}`;
    }

    return {
        field: fieldName,
        // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
        message: message!,
        name,
    };
}
