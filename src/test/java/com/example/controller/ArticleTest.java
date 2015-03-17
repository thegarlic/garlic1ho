package com.example.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.config.ApplicationContext;
import com.example.domain.Article;
import com.example.repository.ArticleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContext.class })
@WebAppConfiguration
@Transactional
public class ArticleTest {

	private static final String TEST = "test";
	private static final String TDD = "TDD";
	private static final String HELLOWORLD = "helloworld";
	@Autowired
	ArticleRepository repository;

	@Before
	public void setup() {
		repository.deleteAll();
		assertEquals(repository.count(), 0);

		Article article = new Article(1L, HELLOWORLD);
		repository.save(article);
		assertEquals(repository.count(), 1);
	}

	@Test
	public void testName() throws Exception {
		assertEquals(1, 1);
	}

	@Test
	public void 저장후_확인보기() throws Exception {
		Article getArticle = repository.getOne(1L);
		assertEquals(getArticle.getMessage(), HELLOWORLD);
	}

	@Test
	public void 수정() throws Exception {
		repository.save(new Article(1L, TDD));
		Article getArticle = repository.getOne(1L);
		assertEquals(getArticle.getMessage(), TDD);
	}

	@Test
	public void 삭제() throws Exception {
		repository.delete(new Article(1L, TEST));
		assertEquals(0, repository.count());
	}

}
