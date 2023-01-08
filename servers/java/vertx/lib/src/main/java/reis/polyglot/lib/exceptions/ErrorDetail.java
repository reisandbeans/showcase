package reis.polyglot.lib.exceptions;

import io.vertx.core.json.JsonObject;

public class ErrorDetail {
    private final String field;
    private final String message;

    public ErrorDetail(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public JsonObject toJson() {
        return new JsonObject()
            .put("message", message)
            .put("field", field);
    }

    public static ErrorDetail fromJson(JsonObject json) {
        return new ErrorDetail(
            json.getString("field"),
            json.getString("message")
        );
    }

    public String toString() {
        return this.toJson().encodePrettily();
    }
}
