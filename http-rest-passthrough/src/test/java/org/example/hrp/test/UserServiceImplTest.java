package org.example.hrp.test;

import static org.hamcrest.CoreMatchers.containsString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultMessage;
import org.example.hrp.beans.Constants;
import org.example.hrp.beans.JacksonObjectMapperBuilder;
import org.example.hrp.beans.User;
import org.example.hrp.beans.UserList;
import org.example.hrp.camel.processors.RequestProcessor;
import org.example.hrp.camel.processors.ResponseProcessor;
import org.example.hrp.spring.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest extends ContextTestSupport {
	
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
		
		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from(Constants.ROUTE_NAME)
				.process(new RequestProcessor())
				.setHeader("Content-Type", constant(Constants.CONTENT_TYPE_JSON))
			    .setHeader("Accept", constant(Constants.CONTENT_TYPE_JSON))
			    .setHeader(Exchange.HTTP_METHOD, constant("GET"))
			    .removeHeader(Exchange.HTTP_PATH)
				.log("${headers}")
				.process(new Processor() {
	                public void process(Exchange exchange) throws Exception {
	                    exchange.getOut().setBody(bodyString);
	                }
	            })
				.process(new ResponseProcessor());
			}
		});
	}
	
	@Test
	public void userListTest() throws Exception {
		UserServiceImpl userService = new UserServiceImpl();
		userService.setCamelContext(context);
		Map<String, Object> headers = new HashMap<>();
		userService.afterPropertiesSet();
		String response = userService.getUsers(headers);
		Assert.assertThat(response, containsString("id123"));
		Assert.assertThat(response, containsString("a user"));
		Assert.assertThat(response, containsString("92998-3874"));
	}
}
