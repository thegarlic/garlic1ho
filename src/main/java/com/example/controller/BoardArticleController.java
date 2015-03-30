package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.domain.BoardArticle;
import com.example.exception.BoardArticleException;
import com.example.service.BoardArticleService;

@Controller
@RequestMapping("/{boardName}board")
@SessionAttributes("updateArticle")
public class BoardArticleController {
	private static final String BOARD_ERROR = "board/error";
	private static final String BOARD_NAME = "boardName";
	private static final String ARTICLES = "articles";
	private static final String ARTICLE = "article";
	private static final String UPDATE_ARTCILE = "updateArticle";
	private static final String BOARD_FORM_UPDATE = "board/formUpdate";
	private static final String BOARD_FORM_CREATE = "board/formCreate";
	private static final String BOARD_READ = "board/read";
	private static final String BOARD_LIST = "board/list";
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardArticleController.class);
	
	@Autowired BoardArticleService serviceBoard;
	
	//List
	@RequestMapping
	public String index(Model model, @PathVariable(BOARD_NAME) String boardName) {
		return pageIndex(model, boardName, 1);
	}
	@RequestMapping("{page}page")
	public String pageIndex(Model model, @PathVariable(BOARD_NAME) String boardName, @PathVariable("page") int page){
		model.addAttribute(ARTICLES, serviceBoard.getArticlePageInfo(page, boardName));
		return BOARD_LIST;
	}
	
	//CreateForm
	@RequestMapping("/write")
	public String formWrite(@PathVariable(BOARD_NAME) String boardName, Model model) {
		model.addAttribute(BOARD_NAME, boardName);
		return BOARD_FORM_CREATE;
	}
	
	//Create
	@RequestMapping(method=RequestMethod.POST)
	public String create(@PathVariable(BOARD_NAME) String boardName, BoardArticle article){
		serviceBoard.create(article, boardName);
		return gotoIndex(boardName);
	}
	//Read
	@RequestMapping("/{id}")
	public String read(@PathVariable("id") Long id, Model model) throws BoardArticleException {
		model.addAttribute(ARTICLE, serviceBoard.readArticle(id));
		return BOARD_READ;
	}
	
	//UpdateForm
	@RequestMapping("/{id}/update")
	public String formUpdate(@PathVariable("id") Long id, Model model) throws BoardArticleException{
		model.addAttribute(UPDATE_ARTCILE, serviceBoard.updateArticleFormLoad(id));
		return BOARD_FORM_UPDATE;
	}
	
	//Update
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String Update(@PathVariable(BOARD_NAME) String boardName, @PathVariable("id") Long id, 
			@ModelAttribute(UPDATE_ARTCILE) BoardArticle article, SessionStatus sessionStatus){
		serviceBoard.create(article, boardName);
		sessionStatus.setComplete();
		return gotoRead(boardName, id);
	}	
	
	//Delete
	@RequestMapping("/{id}/del")
	public String delete(@PathVariable(BOARD_NAME) String boardName, @PathVariable("id") Long id){
		serviceBoard.delete(id);
		return gotoIndex(boardName);
	}


	//-----------------------------------------------------------------
	@RequestMapping("init")
	public String init(@PathVariable(BOARD_NAME) String boardName){
		serviceBoard.initDB();
		return gotoIndex(boardName);
	}
	@RequestMapping("deleteAll")
	public String deleteAll(){
		serviceBoard.deleteALL();
		return gotoIndex("free");
	}
	
	@ExceptionHandler(BoardArticleException.class)
	public String articleNotFound(@PathVariable(BOARD_NAME) String boardName){
		return BOARD_ERROR;
	}
	private String gotoIndex(String boardName) {
		return "redirect:/"+boardName+"board";
	}
	private String gotoRead(String boardName, Long id) {
		return "redirect:/"+boardName+"board/"+id;
	}
}
