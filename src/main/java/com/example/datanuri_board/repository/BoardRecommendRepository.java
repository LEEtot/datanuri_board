package com.example.datanuri_board.repository;

import com.example.datanuri_board.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRecommendRepository extends JpaRepository<Recommend, Long> {

}
