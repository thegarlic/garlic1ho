package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.BoardArticle;
import com.example.repository.BoardArticleRepository;

@Service
@Transactional
public class BoardArticleService {
	

	private static final Logger LOGGER = LoggerFactory.getLogger(BoardArticleService.class);
	private static final String ID = "id";
	private int sizeDefault = 10;
	@Autowired BoardArticleRepository repository;
	
	public Page<BoardArticle> getBoardList(String boardName){
		return getBoardList(1, boardName);
	}
	
	public Page<BoardArticle> getBoardList(int page, String boardName) {
		//TODO 페이지 잘못들어왔을 때 검사해야함		
		Sort sort = new Sort(Direction.DESC, ID);
		Pageable pageable = new PageRequest(page-1, sizeDefault, sort);
		Page<BoardArticle> pageBoard =repository.findByBoardName(pageable, boardName);
		LOGGER.debug(pageBoard.toString());
		return pageBoard;
	}
	
	
	public BoardArticle getBoardArticle(Long id){
		return repository.findOne(id);
	}
	
	public void initDB(){
		for(int i=0;i<50;i++){
			repository.save(new BoardArticle("제목"+i, "free", "닉넴"+i, "content"+i));
		}
		LOGGER.debug("초기화 완료");
	}
	
	
}
