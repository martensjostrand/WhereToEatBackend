package service;

import static org.fest.assertions.api.Assertions.assertThat;
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
	public void test() {
		when(restaurants.getRestaurant(any(Random.class))).thenReturn(expected);
		
		RestaurantSuggestion actual = service.getSuggestion(restaurants, date);
		
		assertThat(actual).isEqualTo(expected);
	}
}
