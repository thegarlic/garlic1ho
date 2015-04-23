package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.BoardArticle;
import com.example.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	public List<Comment> findByNick(String nick);
	public List<Comment> findByArticleOrderByIdDesc(BoardArticle article);
	public Page<Comment> findByArticle(Pageable pageable, BoardArticle article);
	public Long countByArticle(BoardArticle article);
	
	@Modifying(clearAutomatically=true)
	@Query("update Comment t set t.num_like = t.num_like + 1 where t.id = ?1")
    public int updateNumLike(Long id);
}
