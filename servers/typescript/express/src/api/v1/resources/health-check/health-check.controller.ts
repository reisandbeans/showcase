import { Request, Response } from 'express';
import { SuccessResponse } from '@api/v1/lib/responses/success-response';

export function ping(req: Request, res: Response) {
    SuccessResponse.empty().send(res);
}
