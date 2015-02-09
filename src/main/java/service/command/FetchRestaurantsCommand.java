package service.command;

import java.util.Optional;

import service.command.keys.WhereToEatDependencyKeys;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.FetchValue.Response;
import com.yammer.tenacity.core.TenacityCommand;

import domain.Restaurant;
import domain.RestaurantList;

public class FetchRestaurantsCommand extends TenacityCommand<Optional<RestaurantList>>{

	private final RiakClient riakClient;
	private final FetchValue fetchValue; 
	
	public FetchRestaurantsCommand(RiakClient riakClient, FetchValue fetchValue) {
		super(WhereToEatDependencyKeys.RIAK_GET_RESTAURANTS);
		this.fetchValue = fetchValue;
		this.riakClient = riakClient;
	}
	
	@Override
	protected Optional<RestaurantList> run() throws Exception {
		Response response = riakClient.execute(fetchValue);
		return Optional.ofNullable(response.getValue(RestaurantList.class));
	}

	@Override
	protected Optional<RestaurantList> getFallback() {
		return Optional.of(RestaurantList.createFallback(new Restaurant("Failure: Kebaben")));
	}

}
