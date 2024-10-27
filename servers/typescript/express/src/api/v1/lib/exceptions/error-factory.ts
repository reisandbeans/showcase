import { ApplicationException } from '@lib/exceptions/application-exception';

export function urlNotFound(url: string) {
    return ApplicationException.notFound(`URL ${url} does not exist`);
}
