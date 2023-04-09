package com.ssoyoostudy.boardPractice.service;

import com.ssoyoostudy.boardPractice.dto.BoardDto;
import com.ssoyoostudy.boardPractice.entity.BoardEntity;
import com.ssoyoostudy.boardPractice.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDto boardDto) {

        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDto);
        boardRepository.save(boardEntity);
    }

    public List<BoardDto> findAll() {

        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(BoardEntity item : boardEntityList ){
            boardDtoList.add(BoardDto.toBoardDto(item));
        }
        return boardDtoList;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDto findById(Long id){

        Optional<BoardEntity>optionalBoardEntity = boardRepository.findById(id);

        if(!optionalBoardEntity.isPresent()) {
            return null;
        }

        BoardEntity boardEntity = optionalBoardEntity.get();
        BoardDto boardDto = BoardDto.toBoardDto(boardEntity);
        return boardDto;
    }


    public BoardDto update(BoardDto boardDto) {
        BoardEntity entity = BoardEntity.toUpdateEntity(boardDto);
        boardRepository.save(entity);
        return findById(boardDto.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() -1;
        int pageLimit=3;

        Page<BoardEntity>boardEntities =
                boardRepository.findAll(
                PageRequest.of(page,pageLimit, Sort.by(Sort.Direction.DESC,"id")));

        Page<BoardDto> boardDtos =
                boardEntities.map(board -> new BoardDto(
                        board.getId(),
                        board.getBoardWriter(),
                        board.getBoardTitle(),
                        board.getBoardHits(),
                        board.getCreatedTime()
                ));
        return boardDtos;
    }
}

