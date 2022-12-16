package reis.polyglot.lib.validation;

import io.vertx.core.json.JsonObject;
import io.vertx.json.schema.*;
import reis.polyglot.lib.exceptions.ApplicationException;
import reis.polyglot.lib.exceptions.ApplicationExceptionFactory;

import java.util.HashMap;

public class JsonValidator {
    private final HashMap<JsonObject, Validator> validatorCache = new HashMap<>();

    public void validate(JsonObject schema, JsonObject data) {
        Validator validator = this.getValidator(schema);
        OutputUnit result = validator.validate(data);
        if (!result.getValid()) {
            throw this.createValidationException(result);
        }
    }

    private Validator getValidator(JsonObject schemaDefinition) {
        if (!this.validatorCache.containsKey(schemaDefinition)) {
            JsonSchema schema = JsonSchema.of(schemaDefinition);
            Validator validator = Validator.create(schema, new JsonSchemaOptions().setDraft(Draft.DRAFT7).setBaseUri("https://vertx.io"));
            this.validatorCache.put(schemaDefinition, validator);
        }
        return this.validatorCache.get(schemaDefinition);
    }

    private ApplicationException createValidationException(OutputUnit result) {
        ApplicationException exception = ApplicationExceptionFactory.invalidParameters();
        // TODO: map validation errors to error details
        return exception;
    }
}
