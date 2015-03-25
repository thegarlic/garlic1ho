package com.example.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import javax.persistence.Lob;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Entity
public class BoardArticle extends BaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String title;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(
			name = "article_content_holder", 
			joinColumns = @JoinColumn(name = "article_id", 
			foreignKey= @ForeignKey(name="fk_article_content_holder_id"),
			unique = true))
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

	@Column(nullable=true)
	private int num_read;
	
	

	public BoardArticle() {
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


	public int getNum_read() {
		return num_read;
	}

	public void setNum_read(int num_read) {
		this.num_read = num_read;
	}

	

}
