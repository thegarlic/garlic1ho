package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.BoardArticleService;

@RequestMapping("/board")
@Controller
public class BoardArticleController {

	@Autowired BoardArticleService serviceBoard;
	
	@RequestMapping("/{boardName}")
	public String index(Model model) {
		//model.addAttribute("listArticle", serviceBoard.getArticles());
		return "board/index";
	}
}
