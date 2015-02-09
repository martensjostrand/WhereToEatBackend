package service;

import java.security.MessageDigest;
import java.util.Random;

import domain.RestaurantList;
import domain.RestaurantSuggestion;
import error.InternalErrorException;

public class SuggestionService {
	
	private final String algoritm;
	private final String encoding;
	
	public SuggestionService(final String encoding, final String algorithm) {
		this.algoritm = algorithm;
		this.encoding = encoding;
	}
	
	public RestaurantSuggestion getSuggestion(RestaurantList restaurants, String date) {
		try {
			Random random = seedRandom(date);
			return restaurants.getRestaurant(random);
		} catch(Exception exception) {
			throw new InternalErrorException(exception, exception.getMessage());
		}
	}
	
	private Random seedRandom(final String date) throws Exception {
		MessageDigest digest = MessageDigest.getInstance(algoritm);
		byte[] bytes = digest.digest(date.getBytes(encoding));
		String seed = new String(bytes, encoding);
		return new Random(seed.hashCode());
	}
}
