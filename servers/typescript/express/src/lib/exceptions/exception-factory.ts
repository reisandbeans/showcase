import { ApplicationException, ErrorDetail } from '@lib/exceptions/application-exception';
import { ErrorName } from '@lib/exceptions/error-name';

export function invalidParameters(details?: ErrorDetail[]): ApplicationException {
    const exception = ApplicationException.invalidRequest('Invalid parameters provided', ErrorName.InvalidParameters);
    if (details) {
        exception.addDetails(details);
    }
    return exception;
}
