package com.ryo.dgswcinside.dto.request;

public record PublishCommentRequest(
        Long postId,
        String password,
        String nickname,
        String content
) {}
