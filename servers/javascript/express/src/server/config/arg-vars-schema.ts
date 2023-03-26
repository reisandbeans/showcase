import { JSONSchemaType } from 'ajv';
import { ArgConfig, ArgVars } from './config-vars';

export const argvSchema: JSONSchemaType<ArgConfig> = {
    properties: {
        [ArgVars.ServerPort]: {
            maximum: 65535,
            minimum: 1000,
            nullable: true,
            type: 'number',
        },
        [ArgVars.ServerShutDownHandler]: {
            nullable: true,
            type: 'boolean',
        },
    },
    required: [],
    type: 'object',
};
