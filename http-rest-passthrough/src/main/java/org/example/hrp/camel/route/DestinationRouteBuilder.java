package org.example.hrp.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.example.hrp.beans.Constants;
import org.example.hrp.camel.processors.RequestProcessor;
import org.example.hrp.camel.processors.ResponseProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Viswa Ramamoorthy
 *
 */
public class DestinationRouteBuilder extends RouteBuilder {
	
	private Logger logger = LoggerFactory.getLogger(DestinationRouteBuilder.class);

	@Override
	public void configure() throws Exception {
		logger.trace("configure entry >>>");

		addDestinationRoutes();
		
		logger.trace("configure exit <<<");
	}
	
	@SuppressWarnings("unchecked")
	private void addDestinationRoutes() {
		
		//Exception route
        onException()
        .handled(true)
        .maximumRedeliveries(1)
        .logStackTrace(true)
        .logExhausted(true)
        .log(LoggingLevel.ERROR, "Failed processing ${headers}")
        .end();
		
		//Destination route to process user list and users for a specific type
		from(Constants.ROUTE_NAME)
		.process(new RequestProcessor())
		.setHeader("Content-Type", constant(Constants.CONTENT_TYPE_JSON))
	    .setHeader("Accept", constant(Constants.CONTENT_TYPE_JSON))
	    .setHeader(Exchange.HTTP_METHOD, constant("GET"))
	    .removeHeader(Exchange.HTTP_PATH)
		.recipientList(simple("headerAs(targetUrl,String)"))
		.process(new ResponseProcessor());
	}
}
