package service;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import service.command.FetchRestaurantsCommand;
import service.command.factory.FetchRestaurantsCommandFactory;
import domain.Place;
import domain.Restaurant;
import domain.RestaurantList;

public class RestaurantStoreTest {
	
	private final FetchRestaurantsCommandFactory fetchRestaurantsCommandFactory = mock(FetchRestaurantsCommandFactory.class);  
	private final RestaurantStore store = new RestaurantStore(fetchRestaurantsCommandFactory);
	private final List<Restaurant> restaurants = Arrays.asList(new Restaurant("Pizzan"));
	private final Place home = new Place("home");
	
	@Before
	public void setUpCommandFactory() {
		FetchRestaurantsCommand homeCommand = mock(FetchRestaurantsCommand.class);
		when(homeCommand.execute()).thenReturn(Optional.of(new RestaurantList(restaurants)));
		
		when(fetchRestaurantsCommandFactory.create(home)).thenReturn(homeCommand);
	}
	
	@Test
	public void fetchesRestaurantsUsingCommand() {
		RestaurantList actual = store.getRestaurants(home).get();
		RestaurantList expected = new RestaurantList(restaurants);
		assertThat(actual).isEqualTo(expected);
	}
}
