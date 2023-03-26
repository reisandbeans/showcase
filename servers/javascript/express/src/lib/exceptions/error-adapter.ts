import { ApplicationError } from '@lib/exceptions/application-exception';

export type ErrorAdapter<T = Error, U = ApplicationError> = (error: T) => U;
