package com.ryo.dgswcinside.dto.request;

public record PublishPostRequest(
        String password,
        String nickname,
        String title,
        String content
) {}
