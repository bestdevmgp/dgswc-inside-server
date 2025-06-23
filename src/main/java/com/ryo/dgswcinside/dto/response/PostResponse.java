package com.ryo.dgswcinside.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(
    Long id,
    String nickname,
    String title,
    String content,
    LocalDateTime createdAt,
    List<CommentResponse> comments
) {}
