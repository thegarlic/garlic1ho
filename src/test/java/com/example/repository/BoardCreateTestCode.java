package com.example.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;

import junit.framework.Assert;

import org.junit.Before;
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
import com.github.springtestdbunit.DbUnitTestExecutionListener;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@ActiveProfiles("test")
@Transactional
public class BoardCreateTestCode {

	@Autowired
	BoardArticleRepository repository;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	EntityManager em;
	private static final String MESSAGE = "message";	
	private static final String HELLOWORLD = "helloworld";
	
	
	
	@Before
	public void setup() {
		System.out.println("===============");
		System.out.println("테스트 비포어 실행");
		repository.deleteAll();
		System.out.println("삭제 완료~");
		assertEquals(repository.count(), 0);
		BoardArticle article = new BoardArticle(HELLOWORLD, MESSAGE);
		repository.save(article);
		assertEquals(repository.count(), 1);
	}

	@Test
	public void t01_조회수올리기() throws Exception {
		System.out.println("조회수올리기 테스트");
        BoardArticle getArticle = getJustoneArticle();
        repository.updateNumRead(getArticle.getId());
        getArticle = repository.findOne(getArticle.getId());
        assertEquals(1, getArticle.getNum_read().intValue());
	}
	
	
	@Test
	public void t02_레이지로딩하다가옮김의잔해() throws Exception {
		repository.flush();
		BoardArticle  getArticle = getJustoneArticle();
		em=entityManagerFactory.createEntityManager();
		PersistenceUnitUtil unitUtil = em.getEntityManagerFactory().getPersistenceUnitUtil();
		assertTrue(unitUtil.isLoaded(getArticle));
		System.out.println("일단 여기 받고");
		//assertFalse(unitUtil.isLoaded(getArticle, "contentHolder"));
		//이 부분은 @Before나 같은 메서드내에서 repository.save를 하면 에러가 발생한다. 
		System.out.println("받은 메시지 "+getArticle.getContent());
	}
	
	@Test
	public void 글목록삽입() throws Exception {
		repository.deleteAll();
		List<BoardArticle> listBoardArticles = new ArrayList<BoardArticle>();
		for (int i = 0; i < 10; i++) {
			listBoardArticles.add(new BoardArticle("제목"+i, "내용"+i));
		}
		repository.save(listBoardArticles);
		List<BoardArticle> getList = repository.findAll();
		
		for (int i = 0; i < 10; i++) {
			assertEquals("제목"+i, getList.get(i).getTitle());
			assertEquals("내용"+i, getList.get(i).getContent());
		}
	}
	
	@Test
	public void 글하나읽어보기() throws Exception {
		BoardArticle getArticle = getJustoneArticle();
		assertNotNull(getArticle);
		assertNotNull(getArticle.getModificationTime());
		assertNotNull(getArticle.getCreationTime());
		assertEquals(getArticle.getNum_read().intValue(), 0);
		assertEquals(getArticle.getTitle(), HELLOWORLD);
		assertEquals(getArticle.getContent(), MESSAGE);
	}
	
	
	private BoardArticle getJustoneArticle() {
		return repository.findAll().get((int) (repository.count() - 1));
	}
	
	
}
