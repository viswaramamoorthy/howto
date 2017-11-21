package org.example.hrp.spring.service.impl;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.example.hrp.beans.Constants;
import org.example.hrp.spring.service.api.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Viswa Ramamoorthy
 *
 */
@Component
public class UserServiceImpl implements UserService, InitializingBean {

	@Autowired
	private CamelContext camelContext;
	
	private ProducerTemplate producerTemplate;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		producerTemplate = camelContext.createProducerTemplate();
	}

	@Override
	public String getUsers(Map<String, Object> headers) {
		return producerTemplate.requestBodyAndHeaders(Constants.ROUTE_NAME, null, headers, String.class);
	}

	public CamelContext getCamelContext() {
		return camelContext;
	}

	public void setCamelContext(CamelContext camelContext) {
		this.camelContext = camelContext;
	}
}
