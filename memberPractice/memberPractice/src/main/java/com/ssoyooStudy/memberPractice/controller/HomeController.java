package com.ssoyooStudy.memberPractice.controller;

import com.ssoyooStudy.memberPractice.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "index";

    }
}