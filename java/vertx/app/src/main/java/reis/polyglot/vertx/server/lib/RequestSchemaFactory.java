package reis.polyglot.vertx.server.lib;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.Arrays;

public class RequestSchemaFactory {
    private static final JsonObject defaultSchema = new JsonObject()
        .put("type", "object")
        .put("properties", new JsonObject())
        .put("required", new JsonArray());
    private static final JsonObject defaultQuerySchema = new JsonObject()
        .put("type", "object")
        .put("properties", new JsonObject()
            .put("fields", new JsonObject()
                .put("type", "string")
            )
            .put("page", new JsonObject()
                .put("type", "number")
                .put("minimum", 0)
                .put("default", 0)
            )
            .put("pageSize", new JsonObject()
                .put("type", "number")
                .put("minimum", 1)
                .put("default", 50)
            )
            .put("sort", new JsonObject()
                .put("type", "object")
            )
            .put("search", new JsonObject()
                .put("type", "string")
            )
        )
        .put("required", new JsonArray());

    public static JsonObject createBodySchema(JsonObject bodySchema) {
        return createRequestSchema(defaultSchema, bodySchema, defaultSchema);
    }

    public static JsonObject createBodySchema(JsonObject bodySchema, JsonObject paramsSchema) {
        return createRequestSchema(paramsSchema, bodySchema, defaultSchema);
    }

    public static JsonObject createQuerySchema(JsonObject querySchema) {
        return createRequestSchema(defaultSchema, defaultSchema, querySchema);
    }

    public static JsonObject createQuerySchema(JsonObject querySchema, JsonObject paramsSchema) {
        return createRequestSchema(paramsSchema, defaultSchema, querySchema);
    }

    public static JsonObject createParamsSchema(JsonObject paramsSchema) {
        return createRequestSchema(paramsSchema, defaultSchema, defaultSchema);
    }

    public static JsonObject createRequestSchema(
        JsonObject paramsSchema,
        JsonObject bodySchema,
        JsonObject querySchema
    ) {
        JsonObject mergedQuerySchema = new JsonObject().mergeIn(defaultQuerySchema).mergeIn(querySchema, true);
        return new JsonObject()
            .put("type", "object")
            .put("required", new JsonArray(Arrays.asList("body", "params", "query")))
            .put("properties", new JsonObject()
                .put("body", bodySchema)
                .put("params", paramsSchema)
                .put("query", mergedQuerySchema)
            );
    }
}
