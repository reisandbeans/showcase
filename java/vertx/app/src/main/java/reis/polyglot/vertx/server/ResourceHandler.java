package reis.polyglot.vertx.server;

import io.vertx.rxjava3.ext.web.Router;

public interface ResourceHandler {
    Router getHandler();
}
