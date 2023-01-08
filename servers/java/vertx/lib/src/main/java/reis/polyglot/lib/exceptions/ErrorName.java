package reis.polyglot.lib.exceptions;

public class ErrorName {
    private ErrorName() {}

    public static final String BAD_REQUEST = "bad_request";
    public static final String INVALID_PARAMETERS_ERROR = "invalid_parameters";
    public static final String AUTHENTICATION_ERROR = "authentication_error";
    public static final String PERMISSION_ERROR = "missing_permission";
    public static final String NOT_FOUND_ERROR = "not_found";
    public static final String CONFLICT_ERROR = "conflict";
    public static final String INVALID_STATE_ERROR = "invalid_state";
    public static final String TOO_MANY_REQUESTS_ERROR = "too_many_requests";
    public static final String UNEXPECTED_ERROR = "unexpected_error";
    public static final String BAD_GATEWAY_ERROR = "bad_gateway";
    public static final String SERVICE_UNAVAILABLE_ERROR = "service_unavailable";
}
