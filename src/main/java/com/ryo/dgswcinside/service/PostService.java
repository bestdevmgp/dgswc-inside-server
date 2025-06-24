package com.ryo.dgswcinside.service;

import com.ryo.dgswcinside.common.BaseResponse;
import com.ryo.dgswcinside.dto.request.DeleteRequest;
import com.ryo.dgswcinside.dto.request.PublishPostRequest;
import com.ryo.dgswcinside.dto.request.UpdatePostRequest;
import com.ryo.dgswcinside.dto.response.CommentResponse;
import com.ryo.dgswcinside.dto.response.PostResponse;
import com.ryo.dgswcinside.entity.Comment;
import com.ryo.dgswcinside.entity.Post;
import com.ryo.dgswcinside.repository.CommentRepository;
import com.ryo.dgswcinside.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PostService {
    @Autowired private PostRepository postRepository;
    @Autowired private CommentRepository commentRepository;

    public BaseResponse<String> save(PublishPostRequest req) {
        Post post = Post.of(req);
        postRepository.save(post);
        return BaseResponse.ok("게시글 등록 성공");
    }

    public BaseResponse<String> delete(Long id, DeleteRequest req) {
        if (!req.password().equals(postRepository.findById(id).orElseThrow().getPassword())) throw new RuntimeException("잘못된 비밀번호");
        postRepository.deleteById(id);
        return BaseResponse.ok("게시글 삭제 성공");
    }

    public BaseResponse<String> update(Long id, UpdatePostRequest req) {
        Post post = postRepository.findById(id).orElseThrow();
        if (!post.getPassword().equals(req.password())) throw new RuntimeException("잘못된 비밀번호");
        post.update(req);
        postRepository.save(post);
        return BaseResponse.ok("게시글 수정 성공");
    }

    public List<PostResponse> findAll() {
        List<Post> posts =  postRepository.findAll();
        return posts.stream()
                .map(this::convertToPostResponse)
                .toList();
    }

    public PostResponse findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return convertToPostResponse(post);
    }

    private PostResponse convertToPostResponse(Post post) {
        List<Comment> comments = commentRepository.findByPost_Id(post.getId());

        List<CommentResponse> commentResponses = comments.stream()
                .map(comment -> new CommentResponse(
                        comment.getId(),
                        comment.getPostId(),
                        comment.getNickname(),
                        comment.getContent()
                ))
                .toList();

        return new PostResponse(
                post.getId(),
                post.getNickname(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                commentResponses
        );
    }
}