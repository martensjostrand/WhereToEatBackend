package app;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatabaseConfiguration {
	
	private String url = "127.0.0.1";
	private String restaurantBucket = "restaurants";
	private String restaurantType = "default";
	
	@JsonProperty
	public String getUrl() {
		return url;
	}
	
	@JsonProperty
	public void setUrl(String url) {
		this.url = url;
	}
	
	@JsonProperty
	public String getRestaurantBucket() {
		return restaurantBucket;
	}
	
	@JsonProperty
	public void setRestaurantBucket(String restaurantBucket) {
		this.restaurantBucket = restaurantBucket;
	}
	
	@JsonProperty
	public String getRestaurantType() {
		return restaurantType;
	}
	
	@JsonProperty
	public void setRestaurantType(String restaurantType) {
		this.restaurantType = restaurantType;
	}
	
	
}
