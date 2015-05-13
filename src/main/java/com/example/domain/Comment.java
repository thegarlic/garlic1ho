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

import com.example.domain.common.BaseEntity;

@Entity
public class Comment extends BaseEntity<Long> {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length=20)
	private String nick;
	private String password;
	@Type(type="text")
	private String comments;
	
	
	public Comment() {
	}
	public Comment(String nick, String comments) {
		super();
		this.nick = nick;
		this.comments = comments;
	}


	@ManyToOne(targetEntity=BoardArticle.class)
	private BoardArticle article;
    @ManyToOne(targetEntity = User.class, fetch=FetchType.LAZY)
    private User user;
	
	
	public BoardArticle getArticle() {
		return article;
	}
	public void setArticle(BoardArticle article) {
		this.article = article;
	}
	@Column(nullable = true)
	private boolean secret;
	@Column(nullable = true)
	private int num_like;
	
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public boolean isSecret() {
		return secret;
	}
	public void setSecret(boolean secret) {
		this.secret = secret;
	}

	public int getNum_like() {
		return num_like;
	}

	public void setNum_like(int num_like) {
		this.num_like = num_like;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
	public String toString() {
		return "Comment [id=" + id + ", nick=" + nick + ", comments=" + comments + ", secret=" + secret + ", num_like="
				+ num_like + "]";
	}
	
	
	
	

}
