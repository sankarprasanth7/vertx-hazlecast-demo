import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;

import java.util.UUID;

/**
 * @author prasanth
 */
public class HelloConsumeVerticle extends AbstractVerticle {

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		Launcher.clusterEB.consumer("news.uk.sport", message -> {
			System.out.println("I have received a message: " + message.body());
		});

	}
}