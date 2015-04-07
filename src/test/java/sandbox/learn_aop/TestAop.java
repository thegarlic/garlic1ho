package sandbox.learn_aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.config.ApplicationContext;
import com.example.domain.BoardArticle;
import com.example.service.BoardArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={LucyAOPConfigTest.class})
public class TestAop {

	@Autowired LucyTestService service;
	
	@Test
	public void testAop() throws Exception {
		service.create(
	    new BoardArticle("title", "<script>alert(1)</script>"), "free");
	}
}
