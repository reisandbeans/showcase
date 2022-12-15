package reis.polyglot.lib.apiResponses;

import io.vertx.core.json.JsonObject;

public abstract class ApiResponse {
    protected boolean success;
    protected int statusCode;

    public ApiResponse(int statusCode, boolean success) {
        this.statusCode = statusCode;
        this.success = success;
    }

    public JsonObject toJson() {
        return new JsonObject().put("success", this.success);
    }

    public boolean isSuccess() {
        return success;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
