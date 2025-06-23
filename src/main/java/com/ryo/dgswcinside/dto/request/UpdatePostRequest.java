package com.ryo.dgswcinside.dto.request;

public record UpdatePostRequest(
        String password,
        String title,
        String content
) {
}
