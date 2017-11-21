package org.example.hrp.test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.greaterThan;

import java.util.List;

import org.apache.camel.ContextTestSupport;
import org.apache.camel.Route;
import org.example.hrp.camel.route.DestinationRouteBuilder;
import org.example.hrp.camel.route.RestRouteBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserRouteBuilderTest extends ContextTestSupport {
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@Test
	public void routeConfigurationTest() throws Exception {
		context.addRoutes(new RestRouteBuilder());
		context.addRoutes(new DestinationRouteBuilder());
		List<Route> routes = context.getRoutes();
		Assert.assertThat(routes, not(nullValue()));
		Assert.assertThat(routes.size(), greaterThan(3));
	}

}
