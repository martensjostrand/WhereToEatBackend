package health;

import java.nio.charset.Charset;
import com.codahale.metrics.health.HealthCheck;

public class EncodingIsSupportedCheck extends HealthCheck{

	private final String encoding;;
	public EncodingIsSupportedCheck (final String encoding) {
		this.encoding = encoding;
	}
	
	@Override
	protected Result check() throws Exception {
		
		try {
			Charset.forName(encoding);
			return Result.healthy();
		} catch(Exception exception) {
			return Result.unhealthy("Encoding: '" + encoding + "' is not supported on this platform");
		}
		
		
	}

}
