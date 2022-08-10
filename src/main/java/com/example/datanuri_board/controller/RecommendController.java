package com.example.datanuri_board.controller;

import com.example.datanuri_board.dto.RecommendDto;
import com.example.datanuri_board.service.RecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommend")
public class RecommendController    {

    private final RecommendService recommendService;

    @PostMapping
    public ResponseEntity<RecommendDto> recommend(@RequestBody @Valid RecommendDto recommendDto) throws IOException {
        recommendService.recommend(recommendDto);
        return new ResponseEntity<>(recommendDto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<RecommendDto> unRecommend(@RequestBody @Valid RecommendDto recommendDto){
        recommendService.unRecommend(recommendDto);
        return new ResponseEntity<>(recommendDto, HttpStatus.OK);
    }

}
