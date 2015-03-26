package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.BoardArticle;

public interface BoardArticleRepository extends JpaRepository<BoardArticle, Long>{
	
	public Page<BoardArticle> findByBoardName(Pageable pageable, String boardName);
	
	@Modifying(clearAutomatically=true)
	@Query("update BoardArticle t set t.num_read = t.num_read + 1 where t.id = ?1")
    public int updateNumRead(Long id);
}
