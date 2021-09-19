package com.jumia.demo.phonenumbervalidation.controller;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.jumia.demo.phonenumbervalidation.entity.TCustomer;
import com.jumia.demo.phonenumbervalidation.mapper.CustomerMapper;
import com.jumia.demo.phonenumbervalidation.service.CustomerService;

@WebMvcTest({CustomerController.class,CustomerMapper.class})
class CustomerControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CustomerService customerService;
	
	@Autowired
	private CustomerMapper customerMapper;
	@Test
	public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
	  throws Exception {
	    
		TCustomer abdelraoof = new TCustomer();
		abdelraoof.setId(1);
		abdelraoof.setName("Abdelraoof Rabie");
		abdelraoof.setPhone("201025043047");
		abdelraoof.setValid(1);
	    List<TCustomer> allUsers = Arrays.asList(abdelraoof);

	    Mockito.when(customerService.findAll(0, 20)).thenReturn(new PageImpl<TCustomer>(allUsers));

	    mvc.perform(get("/customer?page=0&size=20")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk()).andExpect(jsonPath("$.data.size()").value(1))
	      .andExpect(jsonPath("$.data[0].name").value(abdelraoof.getName()));
	}
}
