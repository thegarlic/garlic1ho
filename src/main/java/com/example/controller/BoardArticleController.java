package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.BoardArticleService;

@RequestMapping("/board")
@Controller
public class BoardArticleController {

	private static final String BOARD_INDEX = "board/index";
	@Autowired BoardArticleService serviceBoard;
	
	@RequestMapping("/{boardName}")
	public String index(Model model, String boardName) {
		model.addAttribute("articles", serviceBoard.getBoardArticle(boardName));
		return BOARD_INDEX;
	}
	@RequestMapping("/{boardName}/{page}page")
	public String pageIndex(Model model, String boardName, int page){
		model.addAttribute("articles", serviceBoard.getBoardArticle(page, boardName));
		return BOARD_INDEX;
	}
}
