package reis.polyglot.vertx;

import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class MainVerticleTest {
    @Test
    @DisplayName("It should start the http server when the verticle is deployed")
    public void testStart(VertxTestContext context) {

    }

    @Test
    @DisplayName("It Should stop the http server when the verticle is stopped")
    public void testStop() {

    }
}
