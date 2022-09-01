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
@RequestMapping("/api/recommend")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @GetMapping
    public List<RecommendResponse> getAllRecommends(@RequestParam Optional<Long> userId, @RequestBody Optional<Long> boardId) {
        return recommendService.getAllRecommendWithParam(userId, boardId);
    }

    @PostMapping
    public Recommend createOneRecommend(@RequestBody RecommendRequest request) {
        return recommendService.createOneRecommend(request);
    }

    @GetMapping("/{recommendId}")
    public Recommend getOneRecommend(@PathVariable Long recommendId) {
        return recommendService.getOneRecommendById(recommendId);
    }

    @DeleteMapping("/{recommendId}")
    public void deleteOneRecommend(@PathVariable Long recommendId) {
        recommendService.deleteOneRecommendById(recommendId);
    }
}
