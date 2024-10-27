import Router from 'express-promise-router';
import { createValidator } from '@api/v1/middleware/request-validator';
import { login } from './user.controller';
import { schemas } from './schemas';

const validate = createValidator();

const router = Router();

router.post('/login', validate(schemas.loginSchema), login);

export { router };
