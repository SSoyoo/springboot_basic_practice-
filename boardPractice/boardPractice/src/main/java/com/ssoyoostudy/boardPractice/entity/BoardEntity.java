package com.ssoyoostudy.boardPractice.entity;

import com.ssoyoostudy.boardPractice.dto.BoardDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column
    private String boardPassword;

    @Column
    private String boardTitle;

    @Column (length = 500)
    private String boardContent;

    @Column
    private int boardHits;

    public static BoardEntity toSaveEntity(BoardDto dto){

        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setBoardWriter(dto.getBoardWriter());
        boardEntity.setBoardPassword(dto.getBoardPassword());
        boardEntity.setBoardTitle(dto.getBoardTitle());
        boardEntity.setBoardContent(dto.getBoardContents());
        boardEntity.setBoardHits(0);

        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDto dto){

        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(dto.getId());
        boardEntity.setBoardWriter(dto.getBoardWriter());
        boardEntity.setBoardPassword(dto.getBoardPassword());
        boardEntity.setBoardTitle(dto.getBoardTitle());
        boardEntity.setBoardContent(dto.getBoardContents());
        boardEntity.setBoardHits(dto.getBoardHits());

        return boardEntity;
    }





}
