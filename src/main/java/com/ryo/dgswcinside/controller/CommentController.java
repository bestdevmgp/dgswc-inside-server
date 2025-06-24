package com.ryo.dgswcinside.controller;

import com.ryo.dgswcinside.common.BaseResponse;
import com.ryo.dgswcinside.dto.request.DeleteRequest;
import com.ryo.dgswcinside.dto.request.PublishCommentRequest;
import com.ryo.dgswcinside.dto.request.UpdateCommentRequest;
import com.ryo.dgswcinside.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@Tag(name = "댓글", description = "댓글 관련 API입니다.")
public class CommentController {
    private final CommentService service;

    @PostMapping
    @Operation(summary = "댓글 작성", description = "댓글을 작성합니다.")
    public BaseResponse<String> save(@RequestBody PublishCommentRequest req) {
        return service.save(req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "댓글 삭제", description = "id로 댓글을 삭제합니다.")
    public BaseResponse<String> delete(@PathVariable Long id, @RequestBody DeleteRequest req) {
        return service.delete(id, req);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "댓글 수정", description = "Path로 id를 전달합니다.")
    public BaseResponse<String> update(@PathVariable Long id, @RequestBody UpdateCommentRequest req) {
        return service.update(id, req);
    }
}
