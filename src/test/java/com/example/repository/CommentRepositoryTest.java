package com.example.repository;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	@Autowired CommentRepository repoComment;
	@Autowired BoardArticleRepository repoArticle;
	

	private static final Logger LOGGER = LoggerFactory.getLogger(CommentRepositoryTest.class);
	@Test
	public void 게시글넣고댓글넣고_게시글을조건으로댓글불러와보기() {
		BoardArticle article = new BoardArticle(JEMOK);
		repoArticle.save(article);
		
		Comment comment = new Comment();
		comment.setNick(HI);
		comment.setArticle(article);		
		repoComment.save(comment);

		Comment getComment = getJustoneComment();
		BoardArticle getArticle= getJustoneArticle();
		assertEquals(getComment.getNick(), HI);
		assertEquals(getArticle.getTitle(), JEMOK);
		
		LOGGER.debug("모조댓글넣기전의 게시글객체 {}",getArticle);
		BoardArticle fakeArticle = new BoardArticle();
		fakeArticle.setId(getArticle.getId());
		LOGGER.debug("불러오기전의 모조객체{}",fakeArticle);
		List<Comment> getComments = repoComment.findByArticleOrderByIdDesc(fakeArticle);
		for (Comment comment2 : getComments) {
			System.out.println("불러온 댓글 :"+comment2);
			assertEquals(comment2.getNick(), HI);
		}
		System.out.println("댓글 개수 :"+repoComment.countByArticle(fakeArticle));
		
	}
	
	
	
	private Comment getJustoneComment(){
		return repoComment.findAll().get((int) (repoComment.count() -1));
	}
	
	private BoardArticle getJustoneArticle() {
		return repoArticle.findAll().get((int) (repoArticle.count() - 1));
	}

}
