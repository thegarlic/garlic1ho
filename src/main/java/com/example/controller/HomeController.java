package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.BoardArticle;
import com.example.domain.Comment;
import com.example.repository.BoardArticleRepository;
import com.example.repository.CommentRepository;

@Controller
public class HomeController {
	@Autowired CommentRepository repository;
	@Autowired BoardArticleRepository repoArticle;
	
	@RequestMapping(value="/", method = {RequestMethod.GET, RequestMethod.HEAD})
	@ResponseBody
	public String hello(){
		return "Hello world ! first board"
				+ "<a href='/thegarlic1ho/freeboard/init'>link!! </a><br><Br>" +
                 "<a href='/thegarlic1ho/freeboard/'>freeboard </a><br><Br>" +
                "if this link is broken,  <a href='/garlic1ho/freeboard/init'> come here~</a>";
	}
	@RequestMapping("test")
	@ResponseBody
	public String hello2(){
		BoardArticle article = new BoardArticle("jemok");
		
		
		Comment comment = new Comment();
		comment.setNick("hi");
		comment.setArticle(article);
		
		
		repository.save(comment);
		
		return "added";
	}
	
}
