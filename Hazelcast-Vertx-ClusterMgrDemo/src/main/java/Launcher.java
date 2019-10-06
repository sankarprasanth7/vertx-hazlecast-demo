import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class Launcher {
	public static EventBus clusterEB = null;

	public static void main(String[] args) {
//		Config hazelcastConfig = new Config();
//
//		hazelcastConfig.getGroupConfig().setName("dev").setPassword("prasanth");

//		ClusterManager mgr = new HazelcastClusterManager();
//
//		EventBusOptions eventBusOptions = new EventBusOptions();
//		eventBusOptions.setClusterPublicHost("127.0.0.1");
//		VertxOptions options = new VertxOptions().setClusterManager(mgr);
//		Vertx.clusteredVertx(options, res -> {
//			if (res.succeeded()) {
//				Vertx vertx = res.result();
//				clusterEB = vertx.eventBus();
//				vertx.deployVerticle(new DemoVerticle());
//			}
//		});

	    //configure client properties
	    ClientConfig config = new ClientConfig();
	    String[] addresses = {"195.201.90.61" + ":" + "5701"};
	    config.getNetworkConfig().setSmartRouting(true).addAddress(addresses);

	   //start Hazelcast client
	   HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(config);
		
//		Config hazelcastConfig = new Config().setLiteMember(true);
//		EventBusOptions eventBusOptions = new EventBusOptions();
//		eventBusOptions.setClusterPublicHost("195.201.90.61");
//		hazelcastConfig.getGroupConfig().setName("dev");
		ClusterManager mgr = new HazelcastClusterManager(hazelcastInstance);
		VertxOptions options = new VertxOptions().setClusterManager(mgr);
		Vertx.clusteredVertx(options, res -> {
			if (res.succeeded()) {
				Vertx vertx = res.result();
				clusterEB = vertx.eventBus();
//				vertx.deployVerticle(new HelloProduceVerticle());
//				vertx.deployVerticle(new HelloConsumeVerticle());
				System.out.println("started hazlecast cluster");
			}else {
				System.out.println("failed");
			}
		});
	}
}
