package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.BoardArticle;
import com.example.domain.Comment;
import com.example.repository.BoardArticleRepository;
import com.example.repository.CommentRepository;

@Service
@Transactional
public class CommentService {

	@Autowired BoardArticleRepository repoBoard;
	@Autowired CommentRepository repoComment;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);
	
	public List<BoardArticle> findAll() {
		return repoBoard.findAll();
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
