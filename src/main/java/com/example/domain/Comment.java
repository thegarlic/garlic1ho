package com.example.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

@Entity
public class Comment extends BaseEntity<Long> {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length=20)
	private String nick;
	@Type(type="text")
	private String comment;
	
	@ManyToOne(
			targetEntity=BoardArticle.class,
			cascade=CascadeType.ALL
			)
	private BoardArticle article;
	
	
	public BoardArticle getArticle() {
		return article;
	}
	public void setArticle(BoardArticle article) {
		this.article = article;
	}
	private boolean secret;
	private int good;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isSecret() {
		return secret;
	}
	public void setSecret(boolean secret) {
		this.secret = secret;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", nick=" + nick + ", comment=" + comment + ", article=" + article + ", secret="
				+ secret + ", good=" + good + "]";
	}
	
	

}
