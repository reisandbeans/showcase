package reis.polyglot.vertx.server.lib;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava3.core.MultiMap;
import io.vertx.rxjava3.ext.web.RoutingContext;
import reis.polyglot.lib.exceptions.ApplicationException;
import reis.polyglot.lib.validation.JsonValidator;

import java.util.Map;

public class RequestValidator implements Handler<RoutingContext> {
    private static final JsonValidator validator = new JsonValidator();

    private final JsonObject schema;

    private RequestValidator(JsonObject schema) {
        this.schema = schema;
    }

    public static RequestValidator forSchema(JsonObject schema) {
        return new RequestValidator(schema);
    }

    public static RequestValidator forSchema(RequestSchema requestSchema) {
        JsonObject schema = requestSchema.getSchema();
        return new RequestValidator(schema);
    }

    @Override
    public void handle(RoutingContext context) {
        JsonObject data = getDataToValidate(context);
        try {
            validator.validate(this.schema, data);
            context.next();
        } catch (ApplicationException e) {
            context.fail(e);
        }
    }

    private JsonObject getDataToValidate(RoutingContext context) {
        MultiMap queryParams = context.queryParams();
        JsonObject query = new JsonObject();
        for (Map.Entry<String, String> entry : queryParams.entries()) {
            query.put(entry.getKey(), entry.getValue());
        }

        Map<String, String> pathParams = context.pathParams();
        JsonObject params = new JsonObject();
        for (Map.Entry<String, String> entry : pathParams.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
        }

        JsonObject body = context.body().asJsonObject();
        return new JsonObject()
            .put("body", body)
            .put("params", params)
            .put("query", query);
    }
}
