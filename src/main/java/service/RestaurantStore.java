package service;

import java.util.Optional;

import service.command.FetchRestaurantsCommand;
import service.command.factory.FetchRestaurantsCommandFactory;
import domain.Place;
import domain.RestaurantList;

public class RestaurantStore {
	
	private final FetchRestaurantsCommandFactory fetchRestaurantsCommandFactory;
	
	public RestaurantStore(final FetchRestaurantsCommandFactory fetchRestaurantsCommandFactory) {
		this.fetchRestaurantsCommandFactory = fetchRestaurantsCommandFactory;
	}
	
	public Optional<RestaurantList> getRestaurants(final Place place) {
		FetchRestaurantsCommand command = fetchRestaurantsCommandFactory.create(place);
		return command.execute();
	}

}
