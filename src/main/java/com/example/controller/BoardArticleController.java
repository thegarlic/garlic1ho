package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.BoardArticleService;

@RequestMapping("/{boardName}board")
@Controller
public class BoardArticleController {
	

	
	private static final String BOARD_READ = "board/read";
	private static final String BOARD_LIST = "board/list";
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardArticleController.class);
	@Autowired BoardArticleService serviceBoard;
	
	@RequestMapping
	public String index(Model model, @PathVariable("boardName") String boardName) {
		LOGGER.debug("가장 기본 페이지~~");
		model.addAttribute("articles", serviceBoard.getBoardList(boardName));
		return BOARD_LIST;
	}
	
	@RequestMapping("{page}page")
	public String pageIndex(Model model, @PathVariable("boardName") String boardName, @PathVariable("page") int page){
		model.addAttribute("articles", serviceBoard.getBoardList(page, boardName));
		return BOARD_LIST;
	}
	
	@RequestMapping("/{id}")
	public String read(@PathVariable("boardName") String boardName , 
			@PathVariable("id") Long id, Model model){
		model.addAttribute("article", serviceBoard.getBoardArticle(id));
		return BOARD_READ;
	}
	
	@RequestMapping("init")
	public String init(@PathVariable("boardName") String boardName){
		serviceBoard.initDB();
		return "redirect:/"+boardName+"board";
	}
}
