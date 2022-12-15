package reis.polyglot.lib.exceptions;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class ApplicationException extends RuntimeException {
    private final int statusCode;
    private final String errorName;
    private List<ErrorDetail> detailList = null;

    public ApplicationException(int statusCode, String errorName, String message) {
        super(message);
        this.statusCode = statusCode;
        this.errorName = errorName;
    }

    public static ApplicationException fromJson(JsonObject json) {
        ApplicationException error = new ApplicationException(
            json.getInteger("statusCode"),
            json.getString("errorName"),
            json.getString("message")
        );
        JsonArray details = json.getJsonArray("details");
        details.forEach(detail -> error.addDetail(ErrorDetail.fromJson((JsonObject) detail)));
        return error;
    }

    public static ApplicationException fromThrowable(Throwable json) {
        return new ApplicationException(500, ErrorName.UNEXPECTED_ERROR, "An unexpected error has happened");
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorName() {
        return errorName;
    }

    public void addDetails(List<ErrorDetail> details) {
        if (this.detailList == null) {
            this.detailList = new ArrayList<>();
        }
        this.detailList.addAll(details);
    }

    public void addDetail(ErrorDetail detail) {
        if (this.detailList == null) {
            this.detailList = new ArrayList<>();
        }
        this.detailList.add(detail);
    }

    public JsonObject toJson() {
        JsonObject error = new JsonObject()
            .put("statusCode", statusCode)
            .put("errorName", errorName)
            .put("message", getMessage());
        error.put("details", getErrorDetailsArray());
        return error;
    }

    @Override
    public String toString() {
        return this.toJson().encode();
    }

    private JsonArray getErrorDetailsArray() {
        JsonArray details = new JsonArray();
        if (detailList != null) {
            detailList.forEach(errorDetail -> details.add(errorDetail.toJson()));
        }
        return details;
    }
}
