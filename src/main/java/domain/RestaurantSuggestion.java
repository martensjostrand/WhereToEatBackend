package domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RestaurantSuggestion {
	@JsonProperty
	private final Restaurant restaurant;
	
	@JsonProperty
	private final Boolean isFallback;

	@JsonCreator
	public RestaurantSuggestion(@JsonProperty("restaurant") Restaurant restaurant, @JsonProperty("isFallback") Boolean isFallback) {
		this.restaurant = restaurant;
		this.isFallback = isFallback;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((isFallback == null) ? 0 : isFallback.hashCode());
		result = prime * result
				+ ((restaurant == null) ? 0 : restaurant.hashCode());
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
		RestaurantSuggestion other = (RestaurantSuggestion) obj;
		if (isFallback == null) {
			if (other.isFallback != null)
				return false;
		} else if (!isFallback.equals(other.isFallback))
			return false;
		if (restaurant == null) {
			if (other.restaurant != null)
				return false;
		} else if (!restaurant.equals(other.restaurant))
			return false;
		return true;
	}
	
	
	
}
