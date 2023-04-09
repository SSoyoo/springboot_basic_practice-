package com.ssoyooStudy.memberPractice.controller;

import com.ssoyooStudy.memberPractice.dto.MemberDto;
import com.ssoyooStudy.memberPractice.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDto memberDto){
        System.out.println("MemberController.save");
        System.out.println("memberDto = " + memberDto);
        memberService.save(memberDto);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }


    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession session){

        MemberDto loginResult = memberService.login(memberDto);
        if(loginResult == null) return "login";
        session.setAttribute("loginEmail",loginResult.getMemberEmail());
        return "main";

    }



    @GetMapping("/member/{id}") // 경로상의 id를 가져옴
    public String findById(@PathVariable Long id, Model model){
        MemberDto memberDto = memberService.findById(id);
        model.addAttribute("member", memberDto);
        return  "detail";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model){
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDto memberDto = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDto);
        return "update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDto memberDto){
        memberService.update(memberDto);
        return "redirect:/member/" + memberDto.getId();
    }

        @GetMapping("/member/delete/{id}")
        public String deleteById(@PathVariable Long id){
            memberService.deleteById(id);
            return "redirect:/member/";
        }

    @GetMapping("/member/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";

        }
    }


