import { ErrorName } from '@lib/exceptions/error-name';

const defaultErrorMessages: Record<number, string> = {
    400: 'Invalid request',
    401: 'Not authenticated',
    403: 'You do not have permission to access the requested resource',
    404: 'Requested resource was not found',
    405: 'Method not supported',
    409: 'Conflict',
    500: 'Unexpected error',
    503: 'Service Unavailable. Please try again later',
};

const errorNameMap: Record<number, ErrorName> = {
    400: ErrorName.InvalidRequest,
    401: ErrorName.NotAuthenticated,
    403: ErrorName.NotEnoughPermissions,
    404: ErrorName.NotFound,
    405: ErrorName.MethodNotSupported,
    409: ErrorName.Conflict,
    500: ErrorName.ServerError,
    503: ErrorName.ServiceUnavailable,
};

export function getErrorNameAndMessage(
    statusCode: number,
    customMessage?: string,
    customName?: string,
): { message: string; name: string } {
    const name = customName || errorNameMap[statusCode] || ErrorName.ServerError;
    const message = customMessage || defaultErrorMessages[statusCode] || defaultErrorMessages[500];
    return { message, name };
}
