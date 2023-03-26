import { JSONSchemaType } from 'ajv';
import { EnvConfig, EnvVars } from './config-vars';

export const envVarsSchema: JSONSchemaType<EnvConfig> = {
    properties: {
        [EnvVars.ServerPort]: {
            nullable: true,
            pattern: '^\\d{4,5}$',
            type: 'string',
        },
        [EnvVars.ServerShutDownHandler]: {
            enum: ['true', 'false'],
            nullable: true,
            type: 'string',
        },
        [EnvVars.NodeEnv]: {
            enum: ['production', 'development'],
            nullable: true,
            type: 'string',
        },
    },
    required: [],
    type: 'object',
};
