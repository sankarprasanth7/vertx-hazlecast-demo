import com.hazelcast.config.Config;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class Launcher {
	public static EventBus clusterEB = null;

	public static void main(String[] args) {
		Config hazelcastConfig = new Config();

		hazelcastConfig.getGroupConfig().setName("my-cluster-name").setPassword("passwd");

		ClusterManager mgr = new HazelcastClusterManager();

		EventBusOptions eventBusOptions =new EventBusOptions();
		eventBusOptions.setClusterPublicHost("127.0.0.1");
		VertxOptions options = new VertxOptions().setClusterManager(mgr).setEventBusOptions(eventBusOptions);
		Vertx.clusteredVertx(options, res -> {
			if (res.succeeded()) {
				Vertx vertx = res.result();
				clusterEB = vertx.eventBus();
				vertx.deployVerticle(new DemoVerticle());
			}
		});

	}
}
