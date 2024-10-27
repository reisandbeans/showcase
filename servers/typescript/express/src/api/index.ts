import Router from 'express-promise-router';
import { Request } from 'express';
import { errorHandler } from '@api/v1/middleware/error-handler';
import { urlNotFound } from '@api/v1/lib/exceptions/error-factory';
import { getRouter as getApiV1Router } from './v1';

export function getRouter() {
    const router = Router();
    router.use('/v1', getApiV1Router());

    router.all('*', (req: Request) => {
        throw urlNotFound(req.url);
    });

    router.use(errorHandler);
    return router;
}
