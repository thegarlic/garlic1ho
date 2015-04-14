package com.example.repository;

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
@Transactional
public class CommentRepositoryTest {

	@Autowired CommentRepository repository;
	@Autowired BoardArticleRepository repoArticle;
	@Test
	public void test() {
		BoardArticle article = new BoardArticle("jemok");
		
		
		Comment comment = new Comment();
		
		comment.setNick("hi");
		comment.setArticle(article);
		/*List<Comment> list =  new ArrayList<Comment>();
		list.add(comment);
		article.setComments(list);*/
		repository.save(comment);
		
		
	}

}
