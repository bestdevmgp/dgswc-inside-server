package com.ryo.dgswcinside.entity;

import com.ryo.dgswcinside.dto.request.PublishCommentRequest;
import com.ryo.dgswcinside.dto.request.UpdateCommentRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_post_id")
    private Post post;

    public Long getPostId() {
        return post != null ? post.getId() : null;
    }

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public static Comment of(PublishCommentRequest request, Post post) {
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setNickname(request.nickname());
        comment.setPassword(request.password());
        comment.setContent(request.content());
        return comment;
    }

    public void update(UpdateCommentRequest request) {
        this.content = request.content();
    }
}