package com.example.controller;

import org.h2.command.dml.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Comment;
import com.example.exception.BoardArticleException;
import com.example.service.CommentService;

@Controller
@RequestMapping("ajax/comments")
public class CommentController {

	private static final int defaultPage = 1;
	private static final String COMMENTS = "comments";
	private static final String BOARD_COMMENTS = "board/comments";

	private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired CommentService service;
	
	//List
	@RequestMapping(value="/{boardArticleId}", method=RequestMethod.GET)
	public String commentList(@PathVariable Long boardArticleId, Model model) throws BoardArticleException{
		return commentListPage(boardArticleId, defaultPage, model);
	}
	@RequestMapping(value="/{boardArticleId}/{page}page", method=RequestMethod.GET)
	public String commentListPage(@PathVariable Long boardArticleId, @PathVariable int page, Model model) throws BoardArticleException{
		model.addAttribute(COMMENTS, service.getCommentPageInfo(boardArticleId, page));
		return BOARD_COMMENTS;
	}
	
	//Create
	@RequestMapping(value="/{boardArticleId}", method=RequestMethod.POST)
	public String create(@PathVariable Long boardArticleId, Comment comment){
		service.save(boardArticleId, comment);
		return "redirect:/ajax/comments/"+boardArticleId;
	}
	
	//Update
	@RequestMapping(value="/{commentId}update" , method=RequestMethod.POST)
	@ResponseBody
	public String update(@PathVariable Long commentId, Comment comment){
		service.update(comment, service.findOne(commentId));
        //TODO 이거 무조건1 하는거 바꿔야 한다.
		return "1";
	}
	
	//Delete
	@RequestMapping(value="/{commentsId}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable Long commentsId){
		LOGGER.debug("{}번에 대한 댓글삭제 요청", commentsId);
		return service.delete(commentsId);
	}
	
	@ExceptionHandler(BoardArticleException.class)
	public String articleNotFound(){
		//TODO 이거 에러좀 바꿔야 -_-
		return COMMENTS;
	}
	
	
}
