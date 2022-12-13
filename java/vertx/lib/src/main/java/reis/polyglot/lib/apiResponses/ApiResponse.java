package reis.polyglot.lib.apiResponses;

import reis.polyglot.lib.exceptions.ApplicationException;

public abstract class ApiResponse<T extends JsonSerializable> {
    boolean success;

    int statusCode;

    T data;

    ApplicationException error;

    public ApiResponse(int statusCode, T data) {
        this.data = data;
        this.error = null;
        this.statusCode = statusCode;
        this.success = true;
    }

    public ApiResponse(int statusCode, ApplicationException error) {
        this.data = null;
        this.error = error;
        this.statusCode = statusCode;
        this.success = false;
    }
}
