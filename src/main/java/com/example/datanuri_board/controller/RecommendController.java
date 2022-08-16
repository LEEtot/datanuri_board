package com.example.datanuri_board.controller;

import com.example.datanuri_board.dto.request.RecommendRequest;
import com.example.datanuri_board.dto.response.RecommendResponse;
import com.example.datanuri_board.entity.Recommend;
import com.example.datanuri_board.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Recommend")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @GetMapping
    public List<RecommendResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestBody Optional<Long> postId) {
        return recommendService.getAllRecommendWithParam(userId, postId);
    }

    @PostMapping
    public Recommend createOneLike(@RequestBody RecommendRequest request) {
        return recommendService.createOneRecommend(request);
    }

    @GetMapping("/{recommendId}")
    public Recommend getOneLike(@PathVariable Long likeId) {
        return recommendService.getOneRecommendById(likeId);
    }

    @DeleteMapping("/{recommendId}")
    public void deleteOneLike(@PathVariable Long likeId) {
        recommendService.deleteOneRecommendById(likeId);
    }
}
