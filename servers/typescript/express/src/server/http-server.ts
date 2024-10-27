import http from 'http';
import { Application } from 'express';
import { logger } from '@logger';
import { serverConfig } from './config/server-config';
import { registerShutdownHandler, ServerWithShutdownHandler } from './shutdown-handler';
import { ServerConfigVars } from '@server/config/config-vars';

export function startServer(app: Application): Promise<http.Server> {
    const server = http.createServer(app);

    return new Promise((resolve) => {
        const port = serverConfig.get(ServerConfigVars.ServerPort);
        server.listen(port, () => {
            if (serverConfig.get(ServerConfigVars.ServerShutDownHandler)) {
                registerShutdownHandler(server as ServerWithShutdownHandler);
            }
            logger.info(`Server listening on port ${port}...`);
            resolve(server);
        });
    });
}
