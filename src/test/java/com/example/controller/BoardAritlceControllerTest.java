package com.example.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.config.ApplicationContext;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.sun.org.apache.xpath.internal.operations.And;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationContext.class)
@TransactionConfiguration
@WebAppConfiguration
@ActiveProfiles("test")
@DatabaseSetup(value={"classpath:dbsetup/boardArticle.xml", "classpath:dbsetup/article_content_holder.xml"})
public class BoardAritlceControllerTest {

	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void index() throws Exception {
		mockMvc.perform(get("/freeboard"))
				.andExpect(status().isOk())
				.andExpect(view().name("board/list"))				
				.andExpect(model().attributeExists("articles"))
				.andExpect(model().hasNoErrors());
	}
	
	//@Test
	public void getId() throws Exception {
		mockMvc.perform(get("/freeboard/4"))
			.andExpect(status().isOk())
			.andExpect(view().name("board/read"))
			.andExpect(model().attributeExists("article"))
			.andExpect(model().hasNoErrors());
	}
}
