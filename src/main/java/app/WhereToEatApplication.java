package app;

import health.AlgorithmIsSupportedCheck;
import health.EncodingIsSupportedCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.SuggestionResource;
import service.RestaurantStore;
import service.SuggestionService;
import service.command.factory.FetchRestaurantsCommandFactory;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.core.query.Namespace;

public class WhereToEatApplication extends Application<WhereToEatConfiguration>{
	
	public static void main(String[] args) throws Exception {
        new WhereToEatApplication().run(args);
    }
	
	@Override
	public String getName() {
		return "Where to Eat Backend";
	}

	@Override
	public void initialize(Bootstrap<WhereToEatConfiguration> configuration) {
		
	}

	@Override
	public void run(WhereToEatConfiguration configuration, Environment environment)
			throws Exception {
		SuggestionService suggestionService = new SuggestionService(configuration.getEncoding(), configuration.getAlgorithm());

		DatabaseConfiguration databaseConfiguration = configuration.getDatabase();
		final RiakClient riakClient = RiakClient.newClient(databaseConfiguration.getUrl());
		Namespace restaurantsNamespace = new Namespace(databaseConfiguration.getRestaurantType(), databaseConfiguration.getRestaurantBucket());
		FetchRestaurantsCommandFactory fetchRestaurantsCommandFactory = new FetchRestaurantsCommandFactory(riakClient, restaurantsNamespace);
		final RestaurantStore restaurantStore = new RestaurantStore(fetchRestaurantsCommandFactory);
		
		final SuggestionResource suggestionResource = new SuggestionResource(suggestionService , restaurantStore);
		environment.jersey().register(suggestionResource);
		
		AlgorithmIsSupportedCheck algorithmCheck = new AlgorithmIsSupportedCheck(configuration.getAlgorithm());
		environment.healthChecks().register("Algorithm check", algorithmCheck);
		
		EncodingIsSupportedCheck encodingCheck = new EncodingIsSupportedCheck(configuration.getEncoding()); 
		environment.healthChecks().register("Encoding check", encodingCheck);
	}

}
