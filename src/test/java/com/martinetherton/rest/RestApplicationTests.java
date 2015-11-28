package com.martinetherton.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestApplication.class)
@WebAppConfiguration
public class RestApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void getOrder() {
		RestTemplate restTemplate = new RestTemplate();
		Order order = restTemplate.getForObject("http://localhost:8080/order", Order.class);
		System.out.println("order" + order);
	}

}
