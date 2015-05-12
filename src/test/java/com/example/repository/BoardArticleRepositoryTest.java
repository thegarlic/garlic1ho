package com.example.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.config.ApplicationContext;
import com.example.config.PersistenceContext;
import com.example.domain.BoardArticle;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContext.class })
@WebAppConfiguration
@ActiveProfiles("test")
/* 잠깐 쓴 다른 방식.
 * @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContext.class })
@WebAppConfiguration
@ContextConfiguration(classes = {PersistenceContext.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
*/
public class BoardArticleRepositoryTest {

	private static final String TDD = "TDD";
	private static final String HELLOWORLD = "helloworld";
	@Autowired
	BoardArticleRepository repository;
    @Autowired
    PasswordEncoder encoder;

	@Before
	public void setup() {
		repository.deleteAll();
		assertEquals(repository.count(), 0);
		BoardArticle article = new BoardArticle(HELLOWORLD);
		repository.save(article);
		assertEquals(repository.count(), 1);
	}

    @Test
    public void 비밀번호입력확인() throws Exception{
        BoardArticle article = new BoardArticle(HELLOWORLD);
        String password = encoder.encode("1234");
        article.setPassword(password);

        repository.save(article);
        BoardArticle getArticle = getJustoneArticle();
        assertEquals(getArticle.getTitle(), HELLOWORLD);
        encoder.matches("1234", getArticle.getPassword());
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
	
	@Test
	@Transactional
	public void 조회수올리기() throws Exception {
        BoardArticle getArticle = getJustoneArticle();
        repository.updateNumRead(getArticle.getId());
        getArticle = repository.findOne(getArticle.getId());
        assertEquals(1, getArticle.getNum_read().intValue());
	}
	

	private BoardArticle getJustoneArticle() {
		return repository.findAll().get((int) (repository.count() - 1));
	}


}
