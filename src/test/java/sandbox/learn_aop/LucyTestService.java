package sandbox.learn_aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.domain.BoardArticle;

public class LucyTestService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LucyTestService.class);

	public void create(BoardArticle article, String boardName) {
		article.setBoardName(boardName);
		//나중에 이 로그로 변한 게시글을 확인해볼 예정
		LOGGER.debug("저장하기전의 게시글 :{}", article);
		//repository.save  어쩌고~~
	}
}
