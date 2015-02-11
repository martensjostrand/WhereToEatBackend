package service;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Test;

import domain.Restaurant;
import domain.RestaurantList;
import domain.RestaurantSuggestion;

public class SuggestionServiceTest {
	
	private final SuggestionService service = new SuggestionService("UTF-8", "MD5");
	private final String date = "2015-05-25";
	private final Restaurant restaurant = new Restaurant("Kebaben");
	private final RestaurantSuggestion expected = new RestaurantSuggestion(restaurant, Boolean.FALSE);
	private final RestaurantList restaurants = mock(RestaurantList.class);
	
	
	@Test
	public void picksRandomRestaurant() {
		when(restaurants.getRestaurant(any(Random.class))).thenReturn(expected);
		RestaurantSuggestion actual = service.getSuggestion(restaurants, date);
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	public void wrapsExceptions() {
		Exception expected = new RuntimeException();
		when(restaurants.getRestaurant(any(Random.class))).thenThrow(expected);
		
		try {
			service.getSuggestion(restaurants, date);
			fail("Expected exception");
		} catch(Exception actual) {
			assertThat(actual.getCause()).isEqualTo(expected);
		} 
		
	}
}
