package org.example.hrp.test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultMessage;
import org.example.hrp.beans.Constants;
import org.example.hrp.beans.JacksonObjectMapperBuilder;
import org.example.hrp.beans.User;
import org.example.hrp.beans.UserList;
import org.example.hrp.camel.processors.ResponseProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Viswa Ramamoorthy
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ResponseProcessorTest extends ContextTestSupport {
	
	@Mock 
	private Exchange exchange; 
	private ObjectMapper objectMapper = JacksonObjectMapperBuilder.mapper().build();
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		Message message = new DefaultMessage(context);
		Map<String, Object> headers = new HashMap<>();
		message.setHeaders(headers);
		UserList inputJson = new UserList();
		List<User> dataList = new ArrayList<>();
		dataList.add(TestUtil.prepareUser());
		inputJson.setUsers(dataList);
		String bodyString = objectMapper.writeValueAsString(inputJson);
		message.setBody(bodyString);
		when(exchange.getIn()).thenReturn(message);
		when(exchange.getOut()).thenReturn(new DefaultMessage(context));
	}

	@Test
	public void validateResponseTest() throws Exception {
		ResponseProcessor responseProcessor = new ResponseProcessor();
		exchange.getIn().removeHeader(Constants.filterParam);
		responseProcessor.process(exchange);
		Assert.assertThat(exchange.getOut().getBody(), not(nullValue()));
		String bodyJson = (String)exchange.getOut().getBody();
		Assert.assertThat(bodyJson, containsString("id123"));
		Assert.assertThat(bodyJson, containsString("a user"));
		Assert.assertThat(bodyJson, containsString("92998-3874"));
	}

	@Test
	public void validateUserTypeResponseTest() throws Exception {
		ResponseProcessor responseProcessor = new ResponseProcessor();
		exchange.getIn().setHeader(Constants.filterParam, "auser");
		responseProcessor.process(exchange);
		Assert.assertThat(exchange.getOut().getBody(), not(nullValue()));
		String bodyJson = (String)exchange.getOut().getBody();
		Assert.assertThat(bodyJson, containsString("id123"));
		Assert.assertThat(bodyJson, containsString("auser"));
		Assert.assertThat(bodyJson, containsString("92998-3874"));
	}

	@Test
	public void validateUserTypeTotalResponseTest() throws Exception {
		ResponseProcessor responseProcessor = new ResponseProcessor();
		exchange.getIn().setHeader(Constants.filterParam, "auser");
		responseProcessor.process(exchange);
		Assert.assertThat(exchange.getOut().getBody(), not(nullValue()));
		String bodyJson = (String)exchange.getOut().getBody();
		Assert.assertThat(bodyJson, containsString("Gwenborough"));
	}

	@Test
	public void validateNoResponseTest() throws Exception {
		UserList inputJson = new UserList();
		String bodyString = objectMapper.writeValueAsString(inputJson);
		exchange.getIn().setBody(bodyString);

		ResponseProcessor responseProcessor = new ResponseProcessor();
		exchange.getIn().removeHeader(Constants.filterParam);
		responseProcessor.process(exchange);
		Assert.assertThat(exchange.getOut().getBody(), not(nullValue()));
		String bodyJson = (String)exchange.getOut().getBody();
		Assert.assertThat(bodyJson, containsString("no users"));
	}
}
