import Router from 'express-promise-router';
import { ping } from './health-check.controller';

const router = Router();

router.all('/ping', ping);

export { router };
