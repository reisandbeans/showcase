import { NextFunction, Request, Response } from 'express';
import { ApplicationException } from '@lib/exceptions/application-exception';
import { ErrorResponse } from '@api/v1/lib/responses/error-response';

import { logger } from '@logger';

export function errorHandler(error: any, req: Request, res: Response, next: NextFunction) {
    const applicationError = ApplicationException.fromError(error);
    const errorResponse = new ErrorResponse(applicationError);

    // TODO: compose proper error message
    logger.error('');

    errorResponse.send(res);
}
