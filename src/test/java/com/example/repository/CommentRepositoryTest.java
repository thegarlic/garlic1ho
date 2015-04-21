package com.example.repository;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
import com.example.domain.Comment;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@ActiveProfiles("test")

public class CommentRepositoryTest {
	
	private static final String JEMOK = "jemok";
	private static final String HI = "hi";
	@Autowired CommentRepository repository;
	@Autowired BoardArticleRepository repoArticle;
	
	@Test
	public void test() {
		BoardArticle article = new BoardArticle(JEMOK);
		Comment comment = new Comment();
		comment.setNick(HI);
		comment.setArticle(article);		
		
		repository.save(comment);
		
		Comment getComment = getJustoneComment();
		BoardArticle getArticle= getJustoneArticle();
		assertEquals(getComment.getNick(), HI);
		assertEquals(getArticle.getTitle(), JEMOK);
	}
	
	
	
	private Comment getJustoneComment(){
		return repository.findAll().get((int) (repository.count() -1));
	}
	
	private BoardArticle getJustoneArticle() {
		return repoArticle.findAll().get((int) (repoArticle.count() - 1));
	}

}
