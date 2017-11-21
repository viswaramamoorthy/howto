package org.example.hrp.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.example.hrp.spring.service.api.UserService;
import org.example.hrp.spring.service.rest.UserController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UserControllerTest {
    
	private MockMvc mockMvc;
	
	@Mock
    private UserService userService;
	
	@InjectMocks
	private UserController userController;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
	}
 
    @Test
    public void userListTest() throws Exception {
        when(userService.getUsers(any())).thenReturn("{\"Message\": \"success\"}");
 
        mockMvc.perform(get("/users/list"))
                .andExpect(status().isOk());
 
        verify(userService, times(1)).getUsers(any());
        verifyNoMoreInteractions(userService);
    }
}