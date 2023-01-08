package reis.polyglot.vertx.server.lib;

import io.vertx.rxjava3.ext.web.Router;

public interface ResourceHandler {
    Router getHandler();
}
