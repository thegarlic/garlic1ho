package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	
	
	@Modifying(clearAutomatically=true)
	@Query("update Comment t set t.num_like = t.num_like + 1 where t.id = ?1")
    public int updateNumLike(Long id);
}
