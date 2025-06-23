package com.ryo.dgswcinside.controller;

import com.ryo.dgswcinside.common.BaseResponse;
import com.ryo.dgswcinside.dto.request.PublishPostRequest;
import com.ryo.dgswcinside.dto.request.UpdatePostRequest;
import com.ryo.dgswcinside.dto.response.PostResponse;
import com.ryo.dgswcinside.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService service;

    @PostMapping
    public BaseResponse<String> save(@RequestBody PublishPostRequest req) {
        return service.save(req);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PatchMapping("/{id}")
    public BaseResponse<String> update(
        @PathVariable Long id,
        @RequestBody UpdatePostRequest req
    ) {
        return service.update(id, req);
    }

    @GetMapping
    public List<PostResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PostResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }
}
