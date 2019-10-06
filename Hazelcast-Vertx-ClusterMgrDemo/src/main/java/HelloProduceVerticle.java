import java.util.UUID;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class HelloProduceVerticle extends AbstractVerticle {
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		vertx.eventBus().publish("news.uk.sport", "Yay! Someone kicked a ball");
	}
}
