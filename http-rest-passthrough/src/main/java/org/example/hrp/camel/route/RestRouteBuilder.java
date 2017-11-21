package org.example.hrp.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.example.hrp.beans.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Viswa Ramamoorthy
 *
 */
public class RestRouteBuilder extends RouteBuilder {
	
	private static final String REST_COMPONENT_NAME="servlet";
	private static final String CAMEL_WS_CONTEXT="/camel";
	private static final String WS_CONTEXT="/users";
	
	private Logger logger = LoggerFactory.getLogger(RestRouteBuilder.class);

	@Override
	public void configure() throws Exception {
		logger.trace("configure entry >>>");

		addRestRoutes();
		
		logger.trace("configure exit <<<");
	}
	
	private void addRestRoutes() {
		restConfiguration().component(REST_COMPONENT_NAME).apiContextPath(CAMEL_WS_CONTEXT);
		
		//User list route
		//Sets up parameter to identify route and sends to destination 
		rest(WS_CONTEXT)
            .consumes(Constants.CONTENT_TYPE_JSON)
            .produces(Constants.CONTENT_TYPE_JSON)
			.get("/list")
			.route()
			.setHeader(Constants.bridgeEndpointParam).constant("true")
			.setHeader("Accept-Encoding", constant("deflate"))
			.to(Constants.ROUTE_NAME);

		//User for a specific type
		//Sets up parameter to identify route and sends to destination 
		rest(WS_CONTEXT)
	        .consumes(Constants.CONTENT_TYPE_JSON)
	        .produces(Constants.CONTENT_TYPE_JSON)
			.get("/list/{username}")
			.route()
			.setHeader(Constants.filterParam).simple("headerAs("+Constants.filterParam+",String)")
			.setHeader(Constants.bridgeEndpointParam).constant("true")
			.setHeader("Accept-Encoding", constant("deflate"))
			.to(Constants.ROUTE_NAME);
	}
}
