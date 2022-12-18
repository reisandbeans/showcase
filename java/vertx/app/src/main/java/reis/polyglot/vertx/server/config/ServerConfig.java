package reis.polyglot.vertx.server.config;

import io.vertx.core.json.JsonObject;

public class ServerConfig {
    public static final String HTTP_SERVER_PORT = "httpServerPort";

    private int httpServerPort;

    ServerConfig(JsonObject config) {
        this.parseConfig(config);
    }

    private void parseConfig(JsonObject config) {
        this.httpServerPort = Integer.parseInt(config.getString(HTTP_SERVER_PORT));
    }

    public int getHttpServerPort() {
        return httpServerPort;
    }
}
