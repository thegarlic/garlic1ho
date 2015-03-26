package com.example.BoardArticle;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.config.PersistenceContext;
import com.example.domain.BoardArticle;
import com.example.repository.BoardArticleRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@ActiveProfiles("test")
@DatabaseSetup(value={"classpath:dbsetup/boardArticle.xml", "classpath:dbsetup/article_content_holder.xml"})
public class LazyLoadingTest {
	@Autowired
	BoardArticleRepository repository;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	EntityManager em;
	
	@Test
	@Transactional
	public void t02_글목록받아와보기() throws Exception {
		//repository.save(new BoardArticle("hello world", "안녕하세요. 메시지입니다"));
		BoardArticle  getArticle = getJustoneArticle();
		em=entityManagerFactory.createEntityManager();
		PersistenceUnitUtil unitUtil = em.getEntityManagerFactory().getPersistenceUnitUtil();
		assertTrue(unitUtil.isLoaded(getArticle));
		System.out.println("아직 레이지 로딩 구문 나오기 전임.");
		assertFalse(unitUtil.isLoaded(getArticle, "contentHolder"));
		System.out.println("받은 메시지 "+getArticle.getContent());
	}
	
	private BoardArticle getJustoneArticle() {
		return repository.findAll().get((int) (repository.count() - 1));
	}
}
