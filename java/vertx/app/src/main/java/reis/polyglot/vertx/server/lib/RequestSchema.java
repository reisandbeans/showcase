package reis.polyglot.vertx.server.lib;

import io.vertx.core.json.JsonObject;

public abstract class RequestSchema {
    public RequestSchema() {}

    public abstract JsonObject getSchema();
}
