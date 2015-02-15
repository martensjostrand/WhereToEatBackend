package service.command;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.yammer.tenacity.testing.TenacityTestRule;

import domain.RestaurantList;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FetchValue.Response.class)
public class FetchRestaurantsCommandTest {

	@Rule
	public final TenacityTestRule tenacityTestRule = new TenacityTestRule();
	
	@Test
	public void fetchesDataFromRiakClient() throws Exception {
		RestaurantList expected = new RestaurantList(null);
		FetchRestaurantsCommand command = commandThatReturns(expected);
		RestaurantList actual = command.execute().get();
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	public void absentWhenClientReturnsNull() throws Exception {
		FetchRestaurantsCommand command = commandThatReturns(null);
		Optional<RestaurantList> actual = command.execute();
		assertThat(actual.isPresent()).isFalse();
	}
	
	private FetchRestaurantsCommand commandThatReturns(RestaurantList restaurantList) throws Exception {
		RiakClient riakClient = mock(RiakClient.class);	
		FetchValue fetchValue = createFetchValue();
		FetchValue.Response response = mock(FetchValue.Response.class);
		when(response.getValue(RestaurantList.class)).thenReturn(restaurantList);
		when(riakClient.execute(fetchValue)).thenReturn(response);
		
		return new FetchRestaurantsCommand(riakClient, fetchValue);
	}
	
	private FetchValue createFetchValue() {
		Location location = new Location(new Namespace("bucket"), "key");
		return new FetchValue.Builder(location).build();
		
	}

}
