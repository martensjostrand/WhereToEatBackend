package resources;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import io.dropwizard.testing.junit.ResourceTestRule;

import java.util.Arrays;
import java.util.Optional;

import javax.ws.rs.core.Response.Status;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import service.RestaurantStore;
import service.SuggestionService;

import com.sun.jersey.api.client.UniformInterfaceException;

import domain.Place;
import domain.Restaurant;
import domain.RestaurantList;
import domain.RestaurantSuggestion;

@RunWith(MockitoJUnitRunner.class)
public class SuggestionResourceTest {
	
	private static final SuggestionService suggestionService = mock(SuggestionService.class);
	private static final RestaurantStore restaurantStore = mock(RestaurantStore.class); 
	
	
	@ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new SuggestionResource(suggestionService, restaurantStore))
            .build();

	@Test
	public void returnsARestaurant() {
		Place place = new Place("1234");
		String date = "2015-02-05";
		
		Restaurant restaurant = new Restaurant("Kebaben");
		RestaurantSuggestion expected = new RestaurantSuggestion(restaurant, Boolean.FALSE);
		RestaurantList restaurants = new RestaurantList(Arrays.asList(restaurant));
		Optional<RestaurantList> optionalRestaurants = Optional.ofNullable(restaurants);
		
		
		when(restaurantStore.getRestaurants(place)).thenReturn(optionalRestaurants);
		when(suggestionService.getSuggestion(restaurants, date)).thenReturn(expected);
	
        assertThat(resources.client().resource("/suggestion/1234/2015-02-05").get(RestaurantSuggestion.class))
        .isEqualTo(expected);
	}
	
	@Test
	public void returns404IfPlaceIsMissing() {
		Optional<RestaurantList> emptyRestaurants = Optional.empty();
		when(restaurantStore.getRestaurants(any(Place.class))).thenReturn(emptyRestaurants);
		try {
			resources.client().resource("/suggetsion/1234/2015-02-05").get(RestaurantSuggestion.class);
			fail("Should have thrown Exception");
		} catch(UniformInterfaceException exception) {
			assertThat(exception.getResponse().getStatus()).isEqualTo(Status.NOT_FOUND.getStatusCode());
		}
	}

}
