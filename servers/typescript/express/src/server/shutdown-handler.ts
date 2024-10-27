import * as http from 'http';
import gracefulShutdown from 'http-graceful-shutdown';
import { logger } from '@logger';
import { serverConfig } from './config/server-config';
import { ServerConfigVars } from '@server/config/config-vars';

let execShutdown: () => Promise<void>;

const DEFAULT_DELAY = 5000;

export interface ServerWithShutdownHandler extends http.Server {
    isShuttingDown: boolean;
}

export interface ShutdownHandlerOptions {
    delay?: number;
}

function cleanup(server: ServerWithShutdownHandler, delay: number = DEFAULT_DELAY): Promise<void> {
    logger.error(`Shutting down server in ${Math.floor(delay / 1000)} seconds...`);
    // Flag set here as well in case the handler was activated by a SIGTERM or SIGINT
    server.isShuttingDown = true;
    return new Promise((resolve) => {
        setTimeout(resolve, delay);
    });
}

function handleError(server: ServerWithShutdownHandler, error: any) {
    // eslint-disable-next-line @typescript-eslint/no-unsafe-argument
    logger.error('Uncaught exception in server', error);
    // Flag set here to prevent successive uncaught exceptions to trigger the shutdown again
    if (!server.isShuttingDown) {
        server.isShuttingDown = true;
        void execShutdown();
    }
}

export function registerShutdownHandler(
    server: ServerWithShutdownHandler,
    opts: ShutdownHandlerOptions = {},
) {
    logger.info('Registering shutdown handler...');

    execShutdown = gracefulShutdown(server, {
        development: !serverConfig.get(ServerConfigVars.IsProduction),
        finally: () => {
            logger.info('Server gracefully closed');
        },
        onShutdown: () => cleanup(server, opts.delay),
        signals: 'SIGINT SIGTERM',
        timeout: 30000,
    });

    process.on('uncaughtException', handleError.bind(null, server));
}
