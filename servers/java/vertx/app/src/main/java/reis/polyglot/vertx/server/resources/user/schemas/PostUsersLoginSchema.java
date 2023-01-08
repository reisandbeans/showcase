package reis.polyglot.vertx.server.resources.user.schemas;

import io.vertx.core.json.JsonObject;
import reis.polyglot.vertx.server.lib.RequestSchema;
import reis.polyglot.vertx.server.lib.RequestSchemaFactory;

public class PostUsersLoginSchema extends RequestSchema {
    @Override
    public JsonObject getSchema() {
        String schema = """
            {
                "type": "object",
                "required": ["username", "password"],
                "properties": {
                    "username": {
                        "type": "string",
                        "minLength": 1
                    },
                    "password": {
                        "type": "string",
                        "minLength": 1
                    }
                }
            }""";
        return RequestSchemaFactory.createBodySchema(new JsonObject(schema));
    }
}
