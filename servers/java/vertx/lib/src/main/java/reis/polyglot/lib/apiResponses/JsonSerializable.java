package reis.polyglot.lib.apiResponses;

import io.vertx.core.json.JsonObject;

public interface JsonSerializable {
    JsonObject toJson();
}
