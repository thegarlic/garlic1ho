package com.example.exception;


public class BoardArticleException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public BoardArticleException() {
		super();
	}
	public BoardArticleException(String string) {
		super(string);
	}
}
