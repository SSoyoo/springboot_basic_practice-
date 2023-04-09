package com.ssoyoostudy.boardPractice.dto;

import com.ssoyoostudy.boardPractice.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private Long id;
    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    public BoardDto(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }

    public static BoardDto toBoardDto(BoardEntity entity){

        BoardDto dto = new BoardDto();

        dto.setId(entity.getId());
        dto.setBoardWriter(entity.getBoardWriter());
        dto.setBoardPassword(entity.getBoardPassword());
        dto.setBoardTitle(entity.getBoardTitle());
        dto.setBoardContents(entity.getBoardContent());
        dto.setBoardHits(entity.getBoardHits());
        dto.setBoardCreatedTime(entity.getCreatedTime());
        dto.setBoardUpdatedTime(entity.getUpdatedTime());

        return dto;

    }




}
