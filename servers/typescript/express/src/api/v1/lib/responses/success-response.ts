import { Response } from 'express';
import { ApiResponse } from './api-response';

export class SuccessResponse<T> extends ApiResponse {
    constructor(statusCode: number, public readonly data: T) {
        super(statusCode, true);
    }

    static created<T>(data: T): SuccessResponse<T> {
        return new SuccessResponse(201, data);
    }

    static empty(): SuccessResponse<null> {
        return new SuccessResponse(204, null);
    }

    static ok<T>(data: T): SuccessResponse<T> {
        return new SuccessResponse(200, data);
    }

    send(res: Response) {
        this.formatAndSend(res, null, this.data);
    }
}
