package health;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.codahale.metrics.health.HealthCheck;

public class AlgorithmIsSupportedCheck extends HealthCheck{
	
	private final String algorithm;
	public AlgorithmIsSupportedCheck(final String algorithm) {
		this.algorithm = algorithm;
	}
	
	@Override
	protected Result check() throws Exception {
		try{
			MessageDigest.getInstance(algorithm);
			return Result.healthy();
		} catch(NoSuchAlgorithmException exception) {
			return Result.unhealthy("Algorithm: '" + algorithm + "' is not supported on this platform");
		}
	}

}
