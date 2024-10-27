import { createBodyRequestSchema } from '@lib/schemas/request-schema';

export interface LoginBody {
    password: string;
    username: string;
}

export const loginSchema = createBodyRequestSchema<LoginBody>({
    properties: {
        password: {
            minLength: 1,
            type: 'string',
        },
        username: {
            minLength: 1,
            type: 'string',
        },
    },
    required: ['password', 'username'],
    type: 'object',
});
