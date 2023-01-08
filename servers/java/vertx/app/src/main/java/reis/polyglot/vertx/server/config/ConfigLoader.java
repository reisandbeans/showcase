package reis.polyglot.vertx.server.config;

import static reis.polyglot.vertx.server.config.ServerConfig.HTTP_SERVER_PORT;

import io.reactivex.rxjava3.core.Single;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava3.config.ConfigRetriever;
import io.vertx.rxjava3.core.Vertx;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigLoader {
    private static final Map<String, String> envVarsNameMap = Stream.of(new String[][] {
        { "HTTP_SERVER_PORT", HTTP_SERVER_PORT },
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    private static final Map<String, String> sysPropertiesNameMap = Stream.of(new String[][] {
        { HTTP_SERVER_PORT, HTTP_SERVER_PORT },
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    private static final JsonObject defaultConfigs = new JsonObject()
        .put(HTTP_SERVER_PORT, "8080");

    public static Single<ServerConfig> loadConfig(Vertx vertx) {
        return buildOptions(vertx).map(ServerConfig::new);
    }

    private static Single<JsonObject> buildOptions(Vertx vertx) {
        return Single.zip(
            loadEnvVars(vertx),
            loadSysVars(vertx),
            (envVars, sysVars) -> defaultConfigs
                .mergeIn(envVars, true)
                .mergeIn(sysVars, true)
        );
    }

    private static Single<JsonObject> loadEnvVars(Vertx vertx) {
        ConfigStoreOptions store = new ConfigStoreOptions()
            .setType("env")
            .setOptional(true)
            .setConfig(new JsonObject().put("raw-data", true));
        ConfigRetrieverOptions options = new ConfigRetrieverOptions().addStore(store);
        return ConfigRetriever.create(vertx, options)
            .setConfigurationProcessor(source -> mapProperties(envVarsNameMap, source))
            .rxGetConfig();
    }

    private static Single<JsonObject> loadSysVars(Vertx vertx) {
        ConfigStoreOptions store = new ConfigStoreOptions()
            .setType("sys")
            .setOptional(true)
            .setConfig(new JsonObject().put("raw-data", true));
        ConfigRetrieverOptions options = new ConfigRetrieverOptions().addStore(store);
        return ConfigRetriever.create(vertx, options)
            .setConfigurationProcessor(source -> mapProperties(sysPropertiesNameMap, source))
            .rxGetConfig();
    }

    private static JsonObject mapProperties(Map<String, String> mapper, JsonObject source) {
        JsonObject result = new JsonObject();
        for (Map.Entry<String, String> entry : mapper.entrySet()) {
            String value = source.getString(entry.getKey());
            if (value != null) {
                result.put(entry.getValue(), value);
            }
        }
        return result;
    }
}
