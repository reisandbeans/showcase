import Router from 'express-promise-router';
import { Router as ExpressRouter } from 'express';
import { mount as mountRoutes } from './routes';

export function getRouter(): ExpressRouter {
    const apiRouter = Router();
    mountRoutes(apiRouter);
    return apiRouter;
}
