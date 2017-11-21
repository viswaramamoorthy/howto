package org.example.hrp.camel.processors;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.StringUtils;
import org.example.hrp.beans.Constants;
import org.example.hrp.beans.JacksonObjectMapperBuilder;
import org.example.hrp.beans.OtherResponseMessage;
import org.example.hrp.beans.User;
import org.example.hrp.beans.UserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Viswa Ramamoorthy
 *
 * To prepare response; it will have logic to prepare response based on input headers
 */
public class ResponseProcessor implements Processor {
	
	private ObjectMapper objectMapper = JacksonObjectMapperBuilder.mapper().build();
	
	private Logger logger = LoggerFactory.getLogger(ResponseProcessor.class);

	public void process(Exchange exchange) {
		logger.trace("process enter >>>");
		String jsonString = exchange.getIn().getBody(String.class);
		UserList usersList = new UserList();
		String errorMessage = "";
		try {
			//Convert from response from user API to Json object
			//that has mappings to convert for the response
			List<User> users = objectMapper.readValue(jsonString, 
					new TypeReference<List<User>>(){});
			usersList.setUsers(users);
			processResponse(exchange, usersList);
			//Preparing error response for use in Exception class
			OtherResponseMessage message = OtherResponseMessage.prepareResponse("message", "Error processing response");
			errorMessage = objectMapper.writeValueAsString(message);
		} catch (Exception e) {
			logger.error("Error processing response", e);
			exchange.getOut().setBody(errorMessage);
		}
		logger.trace("process exit <<<");
	}
	
	/*
	 * Note below a java code based filtering for input = username
	 * IF query capability exists with destination API, we could construct the 
	 * API with query to let the web service give back filtered results
	 */
	private UserList filterByValue(Exchange exchange, 
			UserList usersList) {
		Map<String, Object> headers = exchange.getIn().getHeaders();
		String userName = (String)headers.get(Constants.filterParam);
		if (!StringUtils.isAllEmpty(userName)) {
			logger.debug("typeparm {} value {}", Constants.filterParam,
					userName);
			UserList filteredUserList = new UserList();
			//Filter based on input user type
			filteredUserList.setUsers(
					usersList.getUsers().stream()
					.filter(user -> 
					user.getUserName() != null 
					&& user.getUserName().equals(userName))
					.collect(Collectors.toList()));
			return filteredUserList;
		}
		return usersList;
	}
	
	private void processResponse(Exchange exchange, 
			UserList usersList) throws Exception {
		if (usersList != null && !CollectionUtils.isEmpty(usersList.getUsers())) {
			usersList = filterByValue(exchange, 
					usersList);
			logger.debug("usersList size {}", usersList.getUsers().size());
			exchange.getOut().setBody(objectMapper.writeValueAsString(usersList));
		}
		else {
			OtherResponseMessage message = OtherResponseMessage.prepareResponse("message", "no users found");
			exchange.getOut().setBody(objectMapper.writeValueAsString(message));
		}
	}
}
