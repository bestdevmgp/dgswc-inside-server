package com.ryo.dgswcinside.controller;

import com.ryo.dgswcinside.common.BaseResponse;
import com.ryo.dgswcinside.dto.request.DeleteRequest;
import com.ryo.dgswcinside.dto.request.PublishPostRequest;
import com.ryo.dgswcinside.dto.request.UpdatePostRequest;
import com.ryo.dgswcinside.dto.response.PostResponse;
import com.ryo.dgswcinside.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Tag(name = "게시물", description = "게시물 관련 API입니다.")
public class PostController {
    private final PostService service;

    @PostMapping
    @Operation(summary = "게시물 생성", description = "게시물을 생성합니다.")
    public BaseResponse<String> save(@RequestBody PublishPostRequest req) {
        return service.save(req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "게시물 삭제", description = "id로 게시물을 삭제합니다.")
    public BaseResponse<String> delete(@PathVariable Long id, @RequestBody DeleteRequest req) {
        return service.delete(id, req);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "게시물 수정", description = "Path로 게시물 id를 전달합니다.")
    public BaseResponse<String> update(@PathVariable Long id, @RequestBody UpdatePostRequest req) {
        return service.update(id, req);
    }

    @GetMapping
    @Operation(summary = "게시물 전체 조회", description = "인자는 없습니다.")
    public List<PostResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "id로 게시물 조회", description = "Path로 게시물 id를 전달합니다.")
    public PostResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }
}
