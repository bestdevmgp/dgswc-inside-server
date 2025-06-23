package com.ryo.dgswcinside.dto.response;

public record CommentResponse(
        Long commentId,
        Long postId,
        String nickname,
        String content
) {
}
