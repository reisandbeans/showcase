package reis.polyglot.lib.exceptions;

public class ApplicationExceptionFactory {
    private ApplicationExceptionFactory() {}

    public static ApplicationException NotFound() {
        return new ApplicationException(404, ErrorName.NOT_FOUND_ERROR, "Resource not found");
    }

    public static ApplicationException NotFound(String customMessage) {
        return new ApplicationException(404, ErrorName.NOT_FOUND_ERROR, customMessage);
    }
}
