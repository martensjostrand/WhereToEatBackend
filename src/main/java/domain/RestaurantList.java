package domain;

import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RestaurantList {
	
	@JsonProperty
	private final List<Restaurant> restaurants;
	
	@JsonCreator
	public RestaurantList(@JsonProperty("restaurants") List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
	public Restaurant getRestaurant(Random random) {
		int index = random.nextInt(restaurants.size());
		return restaurants.get(index);
	}
}
