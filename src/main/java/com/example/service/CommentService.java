package com.example.service;

import javax.transaction.Transactional;

import com.example.domain.User;
import com.example.dto.ExampleUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.domain.BoardArticle;
import com.example.domain.Comment;
import com.example.dto.CommentPageInfo;
import com.example.exception.BoardArticleException;
import com.example.repository.BoardArticleRepository;
import com.example.repository.CommentRepository;

@Service
@Transactional
public class CommentService {

	@Autowired BoardArticleRepository repoBoard;
	@Autowired CommentRepository repoComment;
	public static int sizeDefault = 10;
	private static final String ID = "id";
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);
	
	
	
	
	public CommentPageInfo getCommentPageInfo(Long boardArticleId, int page) throws BoardArticleException {
		BoardArticle fakeArticle = new BoardArticle(boardArticleId);
		//댓글 정렬
		Sort sort = new Sort(Direction.DESC, ID);
		Pageable pageable = new PageRequest(page-1, sizeDefault, sort);
		Page<Comment> pageBoard =repoComment.findByArticle(pageable, fakeArticle);
		//댓글 개수 
		long num_comments = repoComment.countByArticle(fakeArticle);
		System.out.println(num_comments);
		//DTO로 넣어줘서 뿌리기
		CommentPageInfo commentPageInfo = new CommentPageInfo(pageBoard, boardArticleId, num_comments);
		LOGGER.debug("코멘트 페이지 정보 : {}", commentPageInfo);
		return commentPageInfo;
	}

	public void save(Long boardArticleId, Comment comment) {
		BoardArticle article = repoBoard.findOne(boardArticleId);
		//comment.setArticle(article);
		comment.setArticle(new BoardArticle(boardArticleId));

        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.debug("시큐리티홀더에서 얻은 유저 정보 {}" , object);
        if(!object.toString().equals("anonymousUser")){
            ExampleUserDetails details = (ExampleUserDetails)object;
            comment.setUser(new User(details.getId()));
            comment.setNick(details.getFirstName());
        }
		LOGGER.debug("article : {}, Comment : {}", article, comment);
		repoComment.save(comment);
	}
	public Comment findOne(Long commentId){
		return repoComment.findOne(commentId);
	}
	
	
	public void update(Comment comment, Comment original){
		LOGGER.debug("저장하기 전의 comment : {}", original);
		original.setComments(comment.getComments());
		LOGGER.debug("설정후 오리지널comment : {} 과 받은 코멘트 : {}", original, comment);
		repoComment.save(original);
	}

	public String delete(Long commentsId) {
		repoComment.delete(commentsId);
		//TODO 여기 삭제 아직은 무조건 1리턴임.
		return "1";
	}
	

	
	
	
}
