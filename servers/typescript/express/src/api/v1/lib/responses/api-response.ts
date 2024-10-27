import { Response } from 'express';
import { ApplicationError } from '@lib/exceptions/application-exception';

export abstract class ApiResponse {
    constructor(
        public readonly statusCode: number,
        public readonly success: boolean,
    ) {}

    protected formatAndSend(
        res: Response,
        error: ApplicationError | null,
        data: any,
    ) {
        const body = {
            data,
            error,
            success: this.success,
        };
        res.status(this.statusCode).json(body);
    }

    abstract send(res: Response): void;
}
