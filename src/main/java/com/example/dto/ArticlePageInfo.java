package com.example.dto;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import com.example.domain.BoardArticle;
import com.example.exception.BoardArticleException;
import com.example.service.BoardArticleService;

public class ArticlePageInfo {
	

	private static final Logger LOGGER = LoggerFactory.getLogger(ArticlePageInfo.class);
	private static int numArticlePerPage = BoardArticleService.sizeDefault;
	private static final int numPages = 10;
	private List<BoardArticle> content;
	// 페이징
	private int numTotalPage; // 총 페이지 수
	private int beginPage; // 페이지 하단의 링크에 쓰일 시작 페이지
	private int endPage; // 페이지 하단의 링크에 쓰일 끝페이지
	private int[] pages;
	private boolean previous;
	private boolean next;

	public ArticlePageInfo() {
	}

	public ArticlePageInfo(Page<BoardArticle> pageBoard) throws BoardArticleException{
		this.numTotalPage = pageBoard.getTotalPages();
		this.beginPage = pageBoard.getNumber() / numArticlePerPage * numArticlePerPage + 1;
		this.endPage = (beginPage + numArticlePerPage - 1) > numTotalPage ? numTotalPage : beginPage + numArticlePerPage - 1;
		this.content = pageBoard.getContent();
		this.previous = beginPage > numPages;
		this.next = endPage < numTotalPage;
		if(endPage<beginPage){
			LOGGER.debug("endPage {} , beginPage{} ", endPage, beginPage);
			throw new BoardArticleException("해당 페이지 목록은 없습니다.");
		}
		this.pages = new int[endPage-beginPage+1];
		for (int i = 0, j=beginPage; i < pages.length; i++, j++) {
			pages[i] = j;
		}
	}

	public List<BoardArticle> getContent() {
		return content;
	}

	public void setContent(List<BoardArticle> content) {
		this.content = content;
	}

	public int getNumTotalPage() {
		return numTotalPage;
	}

	public void setNumTotalPage(int numTotalPage) {
		this.numTotalPage = numTotalPage;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int[] getPages() {
		return pages;
	}

	public void setPages(int[] pages) {
		this.pages = pages;
	}

	public boolean isPrevious() {
		return previous;
	}

	public void setPrevious(boolean previous) {
		this.previous = previous;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public static int getNumarticleperpage() {
		return numArticlePerPage;
	}

	public static int getNumpages() {
		return numPages;
	}

	@Override
	public String toString() {
		return "ArticlePageInfo [content=" + content + ", numTotalPage=" + numTotalPage + ", beginPage=" + beginPage
				+ ", endPage=" + endPage + ", pages=" + Arrays.toString(pages) + ", previous=" + previous + ", next=" + next
				+ "]";
	}
	
	
	
	
	

}
