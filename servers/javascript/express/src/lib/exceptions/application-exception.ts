import { serverConfig } from '@server/config/server-config';
import { getErrorNameAndMessage } from '@lib/exceptions/error-util';
import { ServerConfigVars } from '@server/config/config-vars';

export interface ApplicationError {
    details: ErrorDetail[] | null;
    message: string;
    name: string;
    stack?: string;
    statusCode: number;
}

export interface ErrorDetail {
    field?: string;
    message: string;
    name: string;
}

export class ApplicationException extends Error implements ApplicationError {
    readonly details: ErrorDetail[] = [];

    constructor(
        public readonly statusCode: number,
        public override readonly name: string,
        public override readonly message: string,
    ) {
        super(message);
    }

    static conflict(customMessage?: string, customName?: string): ApplicationException {
        return ApplicationException.createError(409, customMessage, customName);
    }

    static createError(statusCode: number, customMessage?: string, customName?: string) {
        const { message, name } = getErrorNameAndMessage(statusCode, customMessage, customName);
        return new ApplicationException(statusCode, name, message);
    }

    static fromError(error: any): ApplicationException {
        if (error instanceof ApplicationException) {
            return error;
        }
        if (error instanceof Error) {
            return ApplicationException.serverError();
        }
        if (typeof error === 'string') {
            return ApplicationException.serverError();
        }
        return ApplicationException.serverError();
    }

    static invalidRequest(customMessage?: string, customName?: string): ApplicationException {
        return ApplicationException.createError(400, customMessage, customName);
    }

    static methodNotSupported(customMessage?: string, customName?: string): ApplicationException {
        return ApplicationException.createError(405, customMessage, customName);
    }

    static notAuthenticated(customMessage?: string, customName?: string): ApplicationException {
        return ApplicationException.createError(401, customMessage, customName);
    }

    static notEnoughPermissions(customMessage?: string, customName?: string): ApplicationException {
        return ApplicationException.createError(403, customMessage, customName);
    }

    static notFound(customMessage?: string, customName?: string): ApplicationException {
        return ApplicationException.createError(404, customMessage, customName);
    }

    static serverError(customMessage?: string, customName?: string): ApplicationException {
        return ApplicationException.createError(500, customMessage, customName);
    }

    static serviceUnavailable(customMessage?: string, customName?: string): ApplicationException {
        return ApplicationException.createError(503, customMessage, customName);
    }

    addDetails(...errorDetails: ErrorDetail[]) {
        this.details.push(...errorDetails);
    }

    toJson(): ApplicationError {
        const error: ApplicationError = {
            details: this.details,
            message: this.message,
            name: this.message,
            statusCode: this.statusCode,
        };

        if (!serverConfig.get(ServerConfigVars.IsProduction)) {
            error.stack = this.stack;
        }
        return error;
    }
}
