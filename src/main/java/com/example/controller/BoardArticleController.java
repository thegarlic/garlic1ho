package com.example.controller;

import com.example.dto.ExampleUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public static final String BOARD_FORM_PASSWORD = "board/formPassword";
    public static final String FORM_TYPE = "FormType";

    @Autowired BoardArticleService serviceBoard;
	
	
	//List
	@RequestMapping
	public String index(Model model, @PathVariable(BOARD_NAME) String boardName) throws BoardArticleException {
		return pageIndex(model, boardName, 1);
	}
	@RequestMapping("{page}page")
	public String pageIndex(Model model, @PathVariable(BOARD_NAME) String boardName, @PathVariable("page") int page) throws BoardArticleException{
		model.addAttribute(ARTICLES, serviceBoard.getArticlePageInfo(page, boardName));
		LOGGER.debug("articles : {}", ARTICLES);
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
        BoardArticle article = serviceBoard.updateArticleFormLoad(id);
        if(article.getUserid()==null){
            model.addAttribute(ARTICLE, article);
            model.addAttribute(FORM_TYPE, "update");
            return BOARD_FORM_PASSWORD;
        }else{
            Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ExampleUserDetails details = (ExampleUserDetails)object;
            if(details.getId()==article.getUserid()){
                model.addAttribute(UPDATE_ARTCILE, article);
            }else{
                throw new BoardArticleException("글 수정 권한이 없습니다. 어떻게 들어오셨는지요?");
            }
        }
		return BOARD_FORM_UPDATE;
	}
    @RequestMapping(value="/{id}/update", method=RequestMethod.POST)
    public String passwordCheckUpdate(@PathVariable(BOARD_NAME) String boardName, @PathVariable("id") Long id, String password, Model model) throws BoardArticleException {
        BoardArticle article = serviceBoard.checkPassword(password, id);
        if(article != null){
            model.addAttribute(UPDATE_ARTCILE, article);
            return BOARD_FORM_UPDATE;
        }else{
            model.addAttribute("isPassword", false);
            model.addAttribute(FORM_TYPE, "update");
            model.addAttribute(ARTICLE, new BoardArticle(id, boardName));
            return BOARD_FORM_PASSWORD;
        }
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
	public String delete(@PathVariable(BOARD_NAME) String boardName, @PathVariable("id") Long id, Model model) throws BoardArticleException {
        //TODO 리팩토링이 필요하다... 수정폼과 비슷한 구조다. 댓글 수정삭제 권한으로도 이어지면 이거 낭비다.
        BoardArticle article = serviceBoard.updateArticleFormLoad(id);
        if(article.getUserid()==null){
            model.addAttribute(ARTICLE, article);
            model.addAttribute(FORM_TYPE, "del");
            return BOARD_FORM_PASSWORD;
        }else{
            Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ExampleUserDetails details = (ExampleUserDetails)object;
            if(details.getId()==article.getUserid()){
                serviceBoard.delete(id);
            }else{
                throw new BoardArticleException("글 삭제 권한이 없습니다. 어떻게 들어오셨는지요?");
            }
        }
		return gotoIndex(boardName);
	}

    //Delete
    @RequestMapping(value="/{id}/del", method = RequestMethod.POST)
    public String passwordCheckDelete(@PathVariable(BOARD_NAME) String boardName, @PathVariable("id") Long id, String password, Model model) throws BoardArticleException {
        BoardArticle article = serviceBoard.checkPassword(password, id);
        if(article != null){
            serviceBoard.delete(id);
            return gotoIndex(boardName);
        }else{
            model.addAttribute("isPassword", false);
            model.addAttribute("FormType", "del");
            model.addAttribute(ARTICLE, new BoardArticle(id, boardName));
            return BOARD_FORM_PASSWORD;
        }
    }


	//-----------------------------------------------------------------
	@RequestMapping("init")
	public String init(@PathVariable(BOARD_NAME) String boardName){
		serviceBoard.initDB(boardName);
		return gotoIndex(boardName);
	}
	@RequestMapping("deleteAll")
	public String deleteAll(){
		serviceBoard.deleteALL();
		return gotoIndex("free");
	}
	
	@ExceptionHandler(BoardArticleException.class)
	public String articleNotFound(){
		return BOARD_ERROR;
	}
	private String gotoIndex(String boardName) {
		return "redirect:/"+boardName+"board";
	}
	private String gotoRead(String boardName, Long id) {
		return "redirect:/"+boardName+"board/"+id;
	}
}
