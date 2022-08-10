package com.example.datanuri_board.controller;

import com.example.datanuri_board.entity.User;
import com.example.datanuri_board.service.RecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.datanuri_board.config.Constants.*;
import static java.util.Objects.isNull;
import static javax.swing.DropMode.ON;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/recommend")
public class RecommendController    {

    @Autowired
    private RecommendService recommendService;

    @PostMapping
    public void likeOnOff(@RequestParam long boardId,
                          @RequestParam String flag,
                          HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        JSONObject res = new JSONObject();
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if (isNull(loginUser)) {
            res.put(RESULT, INVALID_APPROACH);
        }
        else if (ON.equals(flag)) { // like on
            if (RecommendService.like(loginUser.getUserId(), boardId)) {
                res.put(RESULT, SUCCESS);
            } else {
                res.put(RESULT, FAIL);
            }
        }

        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(res);
    }

}
