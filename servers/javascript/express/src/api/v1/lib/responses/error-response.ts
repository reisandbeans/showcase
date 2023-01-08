import { Response } from 'express';
import { ApplicationError, ApplicationException } from '@lib/exceptions/application-exception';
import { ApiResponse } from './api-response';

export class ErrorResponse extends ApiResponse {
    constructor(private readonly error: ApplicationError) {
        super(error.statusCode, false);
    }

    override send(res: Response) {
        const error = (this.error instanceof ApplicationException) ? this.error.toJson() : this.error;
        this.formatAndSend(res, error, null);
    }
}
