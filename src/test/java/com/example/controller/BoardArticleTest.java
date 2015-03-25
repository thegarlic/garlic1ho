package com.example.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.config.ApplicationContext;
import com.example.domain.BoardArticle;
import com.example.repository.BoardArticleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContext.class })
@WebAppConfiguration
@ActiveProfiles("test")
public class BoardArticleTest {
	private static final String TDD = "TDD";
	private static final String HELLOWORLD = "helloworld";
	@Autowired
	BoardArticleRepository repository;

	@Before
	public void setup() {
		repository.deleteAll();
		assertEquals(repository.count(), 0);
		BoardArticle article = new BoardArticle(HELLOWORLD);
		repository.save(article);
		assertEquals(repository.count(), 1);
	}

	@Test
	public void 저장후_확인보기() throws Exception {
		BoardArticle getArticle = getJustoneArticle();
		assertEquals(getArticle.getTitle(), HELLOWORLD);
		assertNotNull(getArticle.getCreationTime());
	}

	@Test
	public void 수정() throws Exception {
		repository.save(new BoardArticle(TDD));
		BoardArticle getArticle = getJustoneArticle();
		assertEquals(getArticle.getTitle(), TDD);
	}

	@Test
	public void 삭제() throws Exception {
		repository.delete(getJustoneArticle());
		assertEquals(0, repository.count());
	}

	private BoardArticle getJustoneArticle() {
		return repository.findAll().get((int) (repository.count() - 1));
	}

}
