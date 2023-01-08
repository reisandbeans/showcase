import express, { Application } from 'express';
import locale from 'locale';
import bodyParser from 'body-parser';
import { getRouter } from '@api/index';
import { serverConfig } from './server-config';

const { distFolderPath } = serverConfig;

export function buildApp(): Application {
    const app = express();
    setupMiddleware(app);
    bootEndpoints(app);
    serveStaticFiles(app);
    return app;
}

function serveStaticFiles(app: Application) {
    app.get('*.*', express.static(distFolderPath, { maxAge: '1y' }));
    app.get('*', (req, res) => {
        // load the single view file (angular will handle the route changes on the front-end)
        res.sendFile(`${distFolderPath}/index.html`);
    });
}

function setupMiddleware(app: Application) {
    app.set('view engine', 'html');
    app.set('views', distFolderPath);
    app.use(locale(serverConfig.supportedLanguages, serverConfig.defaultLanguage));
    app.use(bodyParser.urlencoded({ extended: true }));
    app.use(bodyParser.json());
}

function bootEndpoints(app: Application) {
    const apiRouter = getRouter();
    app.use('/api', apiRouter);
}
