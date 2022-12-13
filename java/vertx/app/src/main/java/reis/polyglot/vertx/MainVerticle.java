package reis.polyglot.vertx;

import io.reactivex.rxjava3.core.Completable;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava3.core.AbstractVerticle;
import io.vertx.rxjava3.core.MultiMap;
import io.vertx.rxjava3.ext.web.Router;

public class MainVerticle extends AbstractVerticle {
    @Override
    public Completable rxStart() {
        Router router = Router.router(vertx);
        router.route().handler(context -> {
            // Get the address of the request
            String address = context.request().connection().remoteAddress().toString();
            // Get the query parameter "name"
            MultiMap queryParams = context.queryParams();
            String name = queryParams.contains("name") ? queryParams.get("name") : "unknown";
            // Write a json response
            context.json(
                new JsonObject()
                    .put("name", name)
                    .put("address", address)
                    .put("message", "Hello " + name + " connected from " + address)
            );
        });

        // Create the HTTP server
        return vertx.createHttpServer()
            // Handle every request using the router
            .requestHandler(router)
            // Start listening
            .listen(8888)
            // Print the port
            .doOnSuccess(server ->
                System.out.println(
                    "HTTP server started on port " + server.actualPort()
                )
            )
            .ignoreElement();
    }
}
