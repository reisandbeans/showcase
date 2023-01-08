package reis.polyglot.vertx.lib;

import io.vertx.core.json.JsonObject;
import reis.polyglot.vertx.server.config.ServerConfig;

public class MockServerConfig extends ServerConfig {
    public static final JsonObject mockConfig = new JsonObject();

    public MockServerConfig() {
        super(MockServerConfig.mockConfig);
    }
}
