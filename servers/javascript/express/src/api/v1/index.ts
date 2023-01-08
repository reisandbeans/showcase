import { Router } from 'express';
import { mount as mountRoutes } from './routes';

export function getRouter() {
    const apiRouter = Router();
    mountRoutes(apiRouter);
}
