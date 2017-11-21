package org.example.hrp.test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultMessage;
import org.example.hrp.beans.Constants;
import org.example.hrp.camel.processors.RequestProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class RequestProcessorTest extends ContextTestSupport {
	
	@Mock 
	private Exchange exchange; 
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		Message message = new DefaultMessage(context);
		Map<String, Object> headers = new HashMap<>();
		headers.put(Constants.bridgeEndpointParam, "true");
		message.setHeaders(headers);
		when(exchange.getIn()).thenReturn(message);
	}
	
	@Test
	public void bridgeParamTest() throws Exception {
		RequestProcessor requestProcessor = new RequestProcessor();
		requestProcessor.process(exchange);
		Assert.assertThat(exchange.getIn().getHeader("targetUrl"), not(nullValue()));
		String expectedUri = MessageFormat.format(Constants.LIST_TARGET_URI, "true");
		Assert.assertThat(exchange.getIn().getHeader("targetUrl"), equalTo(expectedUri));
	}

	@Test
	public void noBridgeParamTest() throws Exception {
		RequestProcessor requestProcessor = new RequestProcessor();
		exchange.getIn().removeHeader(Constants.bridgeEndpointParam);
		requestProcessor.process(exchange);
		Assert.assertThat(exchange.getIn().getHeader("targetUrl"), not(nullValue()));
		String expectedUri = MessageFormat.format(Constants.LIST_TARGET_URI, "false");
		Assert.assertThat(exchange.getIn().getHeader("targetUrl"), equalTo(expectedUri));
	}
}
