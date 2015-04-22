package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.domain.BoardArticle;
import com.example.domain.Comment;
import com.example.dto.CommentPageInfo;
import com.example.exception.BoardArticleException;
import com.example.repository.BoardArticleRepository;
import com.example.repository.CommentRepository;

@Service
@Transactional
public class CommentService {

	@Autowired BoardArticleRepository repoBoard;
	@Autowired CommentRepository repoComment;
	public static int sizeDefault = 10;
	private static final String ID = "id";
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);
	
	
	
	
	public CommentPageInfo getCommentPageInfo(Long boardArticleId, int page) throws BoardArticleException {
		Sort sort = new Sort(Direction.DESC, ID);
		Pageable pageable = new PageRequest(page-1, sizeDefault, sort);
		Page<Comment> pageBoard =repoComment.findByArticle(pageable, new BoardArticle(boardArticleId));
		CommentPageInfo commentPageInfo = new CommentPageInfo(pageBoard);
		return commentPageInfo;
	}

	public void save(Long boardArticleId, Comment comment) {
		BoardArticle article = repoBoard.findOne(boardArticleId);
		comment.setArticle(article);
		LOGGER.debug("article : {}, Comment : {}", article, comment);
		repoComment.save(comment);
	}

	public String delete(Long commentsId) {
		repoComment.delete(commentsId);
		return "1";
	}
	

	
	
	
}
