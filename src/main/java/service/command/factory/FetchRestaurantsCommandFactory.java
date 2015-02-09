package service.command.factory;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;

import domain.Place;
import service.command.FetchRestaurantsCommand;

public class FetchRestaurantsCommandFactory {

	private final RiakClient riakClient;
	private final Namespace namespace;
	
	public FetchRestaurantsCommandFactory(final RiakClient riakClient, final Namespace namespace) {
		this.riakClient = riakClient;
		this.namespace = namespace;
	}
	
	public FetchRestaurantsCommand create(final Place place) {
		Location location = new Location(namespace, place.getName());
		FetchValue riakCommand = new FetchValue.Builder(location).build();
		return new FetchRestaurantsCommand(riakClient, riakCommand);
	}
}
