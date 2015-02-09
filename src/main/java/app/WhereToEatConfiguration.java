package app;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class WhereToEatConfiguration extends Configuration {
	
	private String encoding = "UTF-8";
	private String algorithm = "MD5";
	private DatabaseConfiguration database;
	
	@JsonProperty
	public DatabaseConfiguration getDatabase() {
		return database;
	}
	
	@JsonProperty
	public void setDatabase(DatabaseConfiguration database) {
		this.database = database;
	}
	
	@JsonProperty
	public String getEncoding() {
		return encoding;
	}

	@JsonProperty
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@JsonProperty
	public String getAlgorithm() {
		return algorithm;
	}

	@JsonProperty
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	
}
