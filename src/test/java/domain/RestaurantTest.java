package domain;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.fest.assertions.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestaurantTest {
	
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Restaurant person = new Restaurant("Kebaben");
        assertThat(MAPPER.writeValueAsString(person))
                .isEqualTo(fixture("fixtures/restaurant.json"));
    }	
}
