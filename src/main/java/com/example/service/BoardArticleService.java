package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.domain.BoardArticle;
import com.example.repository.BoardArticleRepository;

@Service
public class BoardArticleService {
	private static final String ID = "id";
	private int sizeDefault = 10;
	@Autowired BoardArticleRepository repository;
	
	public Page<BoardArticle> getBoardArticle(String boardName){
		return getBoardArticle(1, boardName);
	}
	public Page<BoardArticle> getBoardArticle(int page, String boardName) {
		//TODO 페이지 잘못들어왔을 때 검사해야함		
		Sort sort = new Sort(Direction.DESC, ID);
		Pageable pageable = new PageRequest(page-1, sizeDefault, sort);
		Page<BoardArticle> pageBoard =repository.findByBoardName(pageable, boardName);
		return pageBoard;
	}
	
	
}
