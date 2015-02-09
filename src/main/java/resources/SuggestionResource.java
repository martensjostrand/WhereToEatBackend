package resources;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import service.RestaurantStore;
import service.SuggestionService;
import domain.Place;
import domain.RestaurantList;
import domain.RestaurantSuggestion;

@Path("/suggestion/{place}/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SuggestionResource {
	
	private final SuggestionService suggestionService;
	private final RestaurantStore restaurantStore;
	
	public SuggestionResource(final SuggestionService suggestionService, final RestaurantStore restaurantStore) {
		this.suggestionService = suggestionService;
		this.restaurantStore = restaurantStore;
	}

	@GET
	@Path("{date}")
	public RestaurantSuggestion getSuggestion(@PathParam("place") final Place place,
									@PathParam("date") final String date) {
		Optional<RestaurantList> restaurants = restaurantStore.getRestaurants(place);
		if(restaurants.isPresent()) {
			return suggestionService.getSuggestion(restaurants.get(), date);
		}
		
		throw new WebApplicationException(Status.NOT_FOUND);
	}
}
