package com.ryo.dgswcinside.service;

import com.ryo.dgswcinside.common.BaseResponse;
import com.ryo.dgswcinside.dto.request.PublishCommentRequest;
import com.ryo.dgswcinside.dto.request.UpdateCommentRequest;
import com.ryo.dgswcinside.entity.Comment;
import com.ryo.dgswcinside.entity.Post;
import com.ryo.dgswcinside.repository.CommentRepository;
import com.ryo.dgswcinside.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository repository;
    private final PostRepository postRepository;
    
    public BaseResponse<String> save(PublishCommentRequest req) {
        Post post = postRepository.findById(req.postId())
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다"));
        
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setPassword(req.password());
        comment.setNickname(req.nickname());
        comment.setContent(req.content());
        
        repository.save(comment);
        return BaseResponse.ok("댓글 등록 성공");
    }
    
    public BaseResponse<String> delete(Long id) {
        repository.deleteById(id);
        return BaseResponse.ok("댓글 삭제 성공");
    }
    
    public BaseResponse<String> update(Long id, UpdateCommentRequest req) {
        Comment comment = repository.findById(id).orElseThrow();
        if (!comment.getPassword().equals(req.password())) throw new RuntimeException("잘못된 비밀번호");
        comment.setContent(req.content());
        repository.save(comment);
        return BaseResponse.ok("댓글 수정 성공");
    }
}