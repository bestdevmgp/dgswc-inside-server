package com.ryo.dgswcinside.controller;

import com.ryo.dgswcinside.common.BaseResponse;
import com.ryo.dgswcinside.dto.request.PublishCommentRequest;
import com.ryo.dgswcinside.dto.request.UpdateCommentRequest;
import com.ryo.dgswcinside.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService service;

    @PostMapping
    public BaseResponse<String> save(@RequestBody PublishCommentRequest req) {
        return service.save(req);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PatchMapping("/{id}")
    public BaseResponse<String> update(
        @PathVariable Long id,
        @RequestBody UpdateCommentRequest req
    ) {
        return service.update(id, req);
    }
}
