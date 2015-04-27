package com.example.service;

import com.example.domain.User;
import com.example.dto.ExampleUserDetails;
import com.nhncorp.lucy.security.xss.XssPreventer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.BoardArticle;
import com.example.dto.ArticlePageInfo;
import com.example.exception.BoardArticleException;
import com.example.repository.BoardArticleRepository;


@Service
@Transactional
public class BoardArticleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardArticleService.class);
	private static final String ID = "id";
	public static int sizeDefault = 10;
	@Autowired BoardArticleRepository repository;
	
	//List
	public ArticlePageInfo getArticlePageInfo(String boardName) throws BoardArticleException{
		return getArticlePageInfo(1, boardName);
	}
	
	public ArticlePageInfo getArticlePageInfo(int page, String boardName) throws BoardArticleException {
		//TODO 페이지 잘못들어왔을 때 검사해야함		
		Sort sort = new Sort(Direction.DESC, ID);
		Pageable pageable = new PageRequest(page-1, sizeDefault, sort);
		Page<BoardArticle> pageBoard =repository.findByBoardName(pageable, boardName);
		ArticlePageInfo articlePageInfo = new ArticlePageInfo(pageBoard);
		LOGGER.debug("페이지 인포 정보 {}", articlePageInfo);
		return articlePageInfo;
	}

	
	//Create
	public void create(BoardArticle article, String boardName) {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.debug("시큐리티홀더에서 얻은 유저 정보 {}" , object);
        if(!object.toString().equals("anonymousUser")){
            ExampleUserDetails details = (ExampleUserDetails)object;
            article.setUser(new User(details.getId()));
            article.setUsernick(details.getFirstName());
        }
        article.setBoardName(boardName);
		LOGGER.debug("저장하기전의 게시글 :{}", article);

		repository.save(article);
	}
	//Read
	public BoardArticle readArticle(Long id) throws BoardArticleException{
		repository.updateNumRead(id);
		return getArticle(id);
	}
	//Update
	public BoardArticle updateArticleFormLoad(Long id) throws BoardArticleException{
		return getArticle(id);
	}
	//Delete
	public void delete(Long id) {
		repository.delete(id);
	}
	
	//--------------------------------------------------------
	public void initDB(String boardName){
		for(int i=0;i<50;i++)
			repository.save(new BoardArticle("제목"+i, boardName, "닉넴"+i, "content"+i));
		LOGGER.debug("초기화 완료");
	}
	
	public BoardArticle getArticle(Long id) throws BoardArticleException {
		BoardArticle article = repository.findOne(id);
		if(article==null) throw new BoardArticleException("해당하는 게시글이 없습니다");
		article.setContent(article.getContent());
		LOGGER.debug("불러들인 게시글 {}", article);
		return article;
	}

	public void deleteALL() {
		repository.deleteAll();
	}

	

	
	
}
