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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((isFallback == null) ? 0 : isFallback.hashCode());
		result = prime * result
				+ ((restaurants == null) ? 0 : restaurants.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestaurantList other = (RestaurantList) obj;
		if (isFallback == null) {
			if (other.isFallback != null)
				return false;
		} else if (!isFallback.equals(other.isFallback))
			return false;
		if (restaurants == null) {
			if (other.restaurants != null)
				return false;
		} else if (!restaurants.equals(other.restaurants))
			return false;
		return true;
	}
	
	
}
