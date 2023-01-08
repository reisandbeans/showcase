import Router from 'express-promise-router';
import { login } from './user.controller';

const router = Router();

router.all('/login', login);

export { router };
