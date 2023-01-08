package reis.polyglot.lib.exceptions;

public class ApplicationExceptionFactory {
    private ApplicationExceptionFactory() {}

    public static ApplicationException invalidParameters() {
        return new ApplicationException(400, ErrorName.INVALID_PARAMETERS_ERROR, "Invalid parameters provided");
    }

    public static ApplicationException invalidParameters(String customMessage) {
        return new ApplicationException(400, ErrorName.INVALID_PARAMETERS_ERROR, customMessage);
    }

    public static ApplicationException notFound() {
        return new ApplicationException(404, ErrorName.NOT_FOUND_ERROR, "Resource not found");
    }

    public static ApplicationException notFound(String customMessage) {
        return new ApplicationException(404, ErrorName.NOT_FOUND_ERROR, customMessage);
    }
}
