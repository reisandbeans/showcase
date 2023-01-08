package reis.polyglot.lib.apiResponses;

import io.vertx.core.json.JsonObject;

public class SuccessResponse<T extends JsonSerializable> extends ApiResponse {
    private final T data;

    public SuccessResponse(int statusCode, T data) {
        super(statusCode, true);
        this.data = data;
    }

    public static <T extends JsonSerializable> SuccessResponse<T> ok(T data) {
        return new SuccessResponse<>(200, data);
    }

    public static <T extends JsonSerializable> SuccessResponse<T> created(T data) {
        return new SuccessResponse<>(201, data);
    }

    public static SuccessResponse empty() {
        return new SuccessResponse(204, null);
    }

    public T getData() {
        return data;
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = super.toJson().putNull("error");
        if (data == null) {
            json.putNull("data");
        } else {
            json.put("data", data.toJson());
        }
        return json;
    }
}
