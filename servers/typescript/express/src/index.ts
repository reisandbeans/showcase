import { Application } from 'express';
import { buildApp } from './server/application';
import { startServer } from './server/http-server';

export async function run() {
    const app: Application = buildApp();
    const server = await startServer(app);
    return { app, server };
}

/* istanbul ignore if */
if (!module.parent) {
    void run();
}
