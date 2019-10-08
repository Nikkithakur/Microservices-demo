package com.practice.mypay.dbservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.mypay.dbservice.model.Customer;
import com.practice.mypay.dbservice.model.Wallet;
import com.practice.mypay.dbservice.services.IDatabaseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestControllerUnitTests {

	private final String CREATE_ACCOUNT_URL = "/db/createAccount";
	private final String GET_DETAILS_BY_PHONENUMBER = "/db/{phoneNumber}";
	
	
	private MockMvc mockMvc;
	@MockBean
	private IDatabaseService dbservice;
	@Autowired
	private WebApplicationContext context;
	private Wallet wallet;
	private Customer customer;
	private ObjectMapper object;
	
	@Before
	public void setUp()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		wallet = Wallet.builder().id("00001").balance(new BigDecimal(9999)).build();
		customer = Customer.builder().phoneNumber("9032858312").name("Nikhil").wallet(wallet).build();
		object = new ObjectMapper(); 
	}
	
	@Test 
	public void testCreateAccountAndReturn200() throws Exception 
	{
		Mockito.when(dbservice.createAccountService(Mockito.any(Customer.class))).thenReturn(customer);
		String payload = object.writeValueAsString(customer);
		MvcResult response= mockMvc.perform(post(CREATE_ACCOUNT_URL).content(payload).contentType(MediaType.APPLICATION_JSON))
							.andExpect(status().is(200))
							.andExpect(jsonPath("$.name").value("Nikhil"))
							.andExpect(jsonPath("$.phoneNumber").value("9032858312"))
							.andExpect(jsonPath("$.wallet.transactions").isEmpty())
							.andReturn();
		System.out.println(response.getResponse().getContentAsString());
	}

	@Test
	public void getDetailsUsingPhoneNumber() throws Exception
	{
		Mockito.when(dbservice.getAccountDetailsByPhoneNumberService("9032858312")).thenReturn(customer);
		MvcResult response= mockMvc.perform(get(GET_DETAILS_BY_PHONENUMBER,"9032858312"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Nikhil"))
				.andExpect(jsonPath("$.phoneNumber").value("9032858312"))
				.andExpect(jsonPath("$.wallet.transactions").isEmpty())
				.andReturn();
		System.out.println(response.getResponse().getContentAsString());
	}
}
