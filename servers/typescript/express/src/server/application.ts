import express, { Application } from 'express';
import locale from 'locale';
import bodyParser from 'body-parser';
import { getRouter } from '@api/index';
import { serverConfig } from './config/server-config';
import { ServerConfigVars } from '@server/config/config-vars';

const distFolderPath = serverConfig.get(ServerConfigVars.DistFolderPath);
const supportedLanguages = serverConfig.get(ServerConfigVars.SupportedLanguages);
const defaultLanguage = serverConfig.get(ServerConfigVars.DefaultLanguage);

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
    app.use(locale(supportedLanguages, defaultLanguage));
    app.use(bodyParser.urlencoded({ extended: true }));
    app.use(bodyParser.json());
}

function bootEndpoints(app: Application) {
    const apiRouter = getRouter();
    app.use('/api', apiRouter);
}
