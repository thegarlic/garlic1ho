package com.example.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class BoardArticle extends BaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 100, nullable = false)
	private String title;

	// 여기서부터 게시판 내용글 레이지로딩. 손권남님 위키 onetoone 부분 참고
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "ARTICLE_CONTENT_HOLDER", joinColumns = @JoinColumn(name = "ARTICLE_ID", foreignKey = @ForeignKey(name = "fk_id"), unique = true))
	@Column(name = "content", length = 1024)
	private List<String> contentHolder;

	public List<String> getContentHolder() {
		return contentHolder;
	}

	public void setContentHolder(List<String> contentHolder) {
		this.contentHolder = contentHolder;
	}

	public void setContent(String content) {
		if (getContentHolder() == null) {
			setContentHolder(new ArrayList<String>());
		}
		getContentHolder().clear();
		getContentHolder().add(content);
	}
	public String getContent() {
		if (getContentHolder() == null || getContentHolder().size() == 0) {
			return null;
		}
		return getContentHolder().get(0);
	}

	private String boardName;
	private String usernick;

	@Column(nullable = true)
	private Integer num_read = 0;
	@Column(nullable = true)
	private Integer num_like = 0;
	@Column(nullable = true)
	private Integer num_dislike = 0;

	@OneToMany(targetEntity=Comment.class, 
			mappedBy="article",
			fetch=FetchType.LAZY
			)
	private List<Comment> comments;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public BoardArticle(String title) {
		super();
		this.title = title;
	}

	public BoardArticle(String title, String content) {
		super();
		this.title = title;
		setContent(content);
	}

	public BoardArticle(String title, String content, String boardName) {
		super();
		this.title = title;
		this.boardName = boardName;
		setContent(content);
	}

	public BoardArticle(String title, String boardName, String usernick, String content) {
		super();
		this.title = title;
		this.boardName = boardName;
		this.usernick = usernick;
		setContent(content);
	}

	public BoardArticle() {
	}
	public BoardArticle(Long id){
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNum_read() {
		return num_read;
	}

	public void setNum_read(Integer num_read) {
		this.num_read = num_read;
	}

	public Integer getNum_like() {
		return num_like;
	}

	public void setNum_like(Integer num_like) {
		this.num_like = num_like;
	}

	public Integer getNum_dislike() {
		return num_dislike;
	}

	public void setNum_dislike(Integer num_dislike) {
		this.num_dislike = num_dislike;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getUsernick() {
		return usernick;
	}

	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}

	@Override
	public String toString() {
		return "BoardArticle [id=" + id + ", title=" + title + " content=" + getContent() + ", boardName=" + boardName
				+ ", usernick=" + usernick + ", num_read=" + num_read + ", num_like=" + num_like + ", num_dislike="
				+ num_dislike + "]";
	}

}
