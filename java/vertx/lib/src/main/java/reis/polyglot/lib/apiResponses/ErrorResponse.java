package reis.polyglot.lib.apiResponses;

import io.vertx.core.json.JsonObject;
import reis.polyglot.lib.exceptions.ApplicationException;
import reis.polyglot.lib.exceptions.ApplicationExceptionFactory;

public class ErrorResponse extends ApiResponse {
    private final ApplicationException error;

    private ErrorResponse(ApplicationException error) {
        super(error.getStatusCode(), false);
        this.error = error;
    }

    public static ErrorResponse fromException(ApplicationException exception) {
        return new ErrorResponse(exception);
    }

    public static ErrorResponse notFound() {
        return ErrorResponse.fromException(ApplicationExceptionFactory.NotFound());
    }

    @Override
    public JsonObject toJson() {
        return super.toJson()
            .putNull("data")
            .put("error", this.error.toJson());
    }
}
