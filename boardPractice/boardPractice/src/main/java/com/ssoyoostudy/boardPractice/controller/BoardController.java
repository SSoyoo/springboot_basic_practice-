package com.ssoyoostudy.boardPractice.controller;

import com.ssoyoostudy.boardPractice.dto.BoardDto;
import com.ssoyoostudy.boardPractice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDto boardDto) {
        boardService.save(boardDto);
        return "redirect:/board/";

    }

    @GetMapping("/")
    public String findAll(Model model){
        List<BoardDto>boardDtoList = boardService.findAll();
        model.addAttribute("boardList",boardDtoList);
        return "list";

    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page =1 ) Pageable pageable){

        boardService.updateHits(id);
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("board",boardDto);
        model.addAttribute("page",pageable.getPageNumber());
        return "detail";

    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        BoardDto dto = boardService.findById(id);
        model.addAttribute("updateBoard",dto);
        return "update";

    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDto boardDto, Model model){
        BoardDto board = boardService.update(boardDto);
        model.addAttribute("board",board);
        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        boardService.delete(id);

        return "redirect:/board/";
    }

    @GetMapping("/paging")
    public String paging(@PageableDefault(page =1)Pageable pageable, Model model) {

        pageable.getPageNumber();
        Page<BoardDto>  boardList = boardService.paging(pageable);

        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        return "paging";

    }


}