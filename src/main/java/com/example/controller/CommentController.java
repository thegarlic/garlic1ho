package com.example.controller;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.BoardArticle;
import com.example.domain.Comment;
import com.example.repository.BoardArticleRepository;
import com.example.repository.CommentRepository;
import com.example.service.CommentService;

@Controller
@RequestMapping("ajax/comments")
public class CommentController {

	private static final String COMMENTS = "comments";
	private static final String BOARD_COMMENTS = "board/comments";

	private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired CommentService service;
	
	//List
	@RequestMapping(value="/{boardArticleId}", method=RequestMethod.GET)
	public String commentList(@PathVariable Long boardArticleId, Model model){
		model.addAttribute(COMMENTS, service.findAll());
		return BOARD_COMMENTS;
	}
	
	//Create
	@RequestMapping(value="/{boardArticleId}", method=RequestMethod.POST)
	public String create(@PathVariable Long boardArticleId, Comment comment){
		service.save(boardArticleId, comment);
		return "redirect:/ajax/comments/"+boardArticleId;
	}
	
	//Delete
	@RequestMapping(value="/{commentsId}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable Long commentsId){
		LOGGER.debug("{}번에 대한 댓글삭제 요청", commentsId);
		return service.delete(commentsId);
	}
	
	
}
