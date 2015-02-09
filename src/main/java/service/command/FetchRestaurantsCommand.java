package service.command;

import java.util.Arrays;

import service.command.keys.WhereToEatDependencyKeys;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.FetchValue.Response;
import com.yammer.tenacity.core.TenacityCommand;

import domain.Restaurant;
import domain.RestaurantList;

public class FetchRestaurantsCommand extends TenacityCommand<RestaurantList>{

	private final RiakClient riakClient;
	private final FetchValue fetchValue; 
	
	public FetchRestaurantsCommand(RiakClient riakClient, FetchValue fetchValue) {
		super(WhereToEatDependencyKeys.RIAK_GET_RESTAURANTS);
		this.fetchValue = fetchValue;
		this.riakClient = riakClient;
	}
	
	@Override
	protected RestaurantList run() throws Exception {
		Response response = riakClient.execute(fetchValue);
		return response.getValue(RestaurantList.class);
	}

	@Override
	protected RestaurantList getFallback() {
		return new RestaurantList(Arrays.asList(new Restaurant("Failure: Kebaben")));
	}

}
