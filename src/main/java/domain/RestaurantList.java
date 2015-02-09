package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RestaurantList {
	
	@JsonProperty
	private final List<Restaurant> restaurants;
	
	private Boolean isFallback;
	
	@JsonCreator
	public RestaurantList(@JsonProperty("restaurants") List<Restaurant> restaurants) {
		this(restaurants, Boolean.FALSE);
	}
	
	private RestaurantList(List<Restaurant> restaurants, Boolean isFallback) {
		this.isFallback = isFallback;
		this.restaurants = restaurants;
	}
	
	public static RestaurantList createFallback(Restaurant restaurant) {
		return new RestaurantList(Arrays.asList(restaurant), Boolean.TRUE);
	}
	
	public RestaurantSuggestion getRestaurant(Random random) {
		int index = random.nextInt(restaurants.size());
		return new RestaurantSuggestion(restaurants.get(index), isFallback);
	}
}
