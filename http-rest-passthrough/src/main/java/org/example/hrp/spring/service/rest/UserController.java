package org.example.hrp.spring.service.rest;

import java.util.HashMap;
import java.util.Map;

import org.example.hrp.beans.Constants;
import org.example.hrp.spring.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Viswa Ramamoorthy
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"/list", "/list/{username}"},method=RequestMethod.GET)
	//Note that username is optional here; when it is not present, 
	//entire list will be returned
	public String getUserList(@PathVariable(name="username", required=false) 
				String userType) {
		logger.trace("getUserList entry >>>");
		Map<String, Object> headers = new HashMap<>();
		//If you are wondering why a null or empty check missing
		//Destination service does this check and hence skipping here; one less IF :-)
		headers.put(Constants.filterParam, userType);
		logger.debug("getUserList {} is {}", Constants.filterParam, userType);
		logger.trace("getUserList exit <<<");
		return userService.getUsers(headers);
	}
}
