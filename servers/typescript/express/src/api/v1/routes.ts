import { Router } from 'express';
import { healthCheckRouter, userRouter } from './resources';

export function mount(router: Router) {
    router.use('/health-check', healthCheckRouter);
    router.use('/users', userRouter);
}
