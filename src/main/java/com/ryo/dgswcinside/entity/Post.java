package com.ryo.dgswcinside.entity;

import com.ryo.dgswcinside.dto.request.PublishPostRequest;
import com.ryo.dgswcinside.dto.request.UpdatePostRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public static Post of(PublishPostRequest request) {
        Post post = new Post();
        post.setNickname(request.nickname());
        post.setPassword(request.password());
        post.setTitle(request.title());
        post.setContent(request.content());
        return post;
    }

    public void update(UpdatePostRequest request) {
        this.title = request.title();
        this.content = request.content();
    }
}