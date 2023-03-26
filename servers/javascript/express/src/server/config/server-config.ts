import yargs from 'yargs';
import { hideBin } from 'yargs/helpers';
import { $enum } from 'ts-enum-util';
import { cloneDeep, pick } from 'lodash';
import { resolve } from 'path';
import { JsonValidator } from '@lib/validation/json-validator';
import { ArgConfig, ArgVars, EnvConfig, EnvVars, ServerConfig, ServerConfigVars } from '@server/config/config-vars';
import { argvSchema } from '@server/config/arg-vars-schema';
import { envVarsSchema } from '@server/config/env-vars-schema';

export const ENV_VARS = $enum(EnvVars).getValues();

export const ARG_VARS = $enum(ArgVars).getValues();

export const DEFAULTS: ServerConfig = {
    defaultLanguage: 'en',
    distFolderPath: resolve(process.cwd(), './dist/client'),
    isProduction: false,
    serverPort: 8080,
    serverShutdownHandler: false,
    supportedLanguages: ['en'],
};

export class ConfigService {
    private readonly config: ServerConfig;

    constructor() {
        this.config = this.loadConfig();
    }

    get<K extends ServerConfigVars>(property: K): ServerConfig[K] {
        return this.config[property];
    }

    private loadConfig(): ServerConfig {
        const { argv } = yargs(hideBin(process.argv));
        const relevantEnvVars = pick(process.env, ENV_VARS) as EnvConfig;
        const argvCopy = pick(cloneDeep(argv), ARG_VARS) as ArgConfig;

        const validator = new JsonValidator();
        validator.validateSync(validator.compile(argvSchema), validator, argvCopy);
        validator.validateSync(validator.compile(envVarsSchema), validator, relevantEnvVars);

        const mappedEnvVars = this.mapEnvVars(relevantEnvVars);

        return Object.assign({}, DEFAULTS, mappedEnvVars, argvCopy) as ServerConfig;
    }

    private mapEnvVars(envVars: EnvConfig): Partial<ServerConfig> {
        const mappedVars: Partial<ServerConfig> = {};
        if (envVars[EnvVars.NodeEnv] === 'production') {
            mappedVars[ServerConfigVars.IsProduction] = true;
        }
        if (envVars[EnvVars.ServerPort]) {
            mappedVars[ServerConfigVars.ServerPort] = Number(envVars[EnvVars.ServerPort]);
        }
        if (envVars[EnvVars.ServerShutDownHandler]) {
            mappedVars[ServerConfigVars.ServerShutDownHandler] = envVars[EnvVars.ServerShutDownHandler] === 'true';
        }
        return mappedVars;
    }
}

export const serverConfig = new ConfigService();
