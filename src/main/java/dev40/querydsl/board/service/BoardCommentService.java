package dev40.querydsl.board.service;

import dev40.querydsl.board.entity.BoardCommentEntity;
import dev40.querydsl.repository.BoardCommentRepository;
import dev40.querydsl.board.model.BoardCommentDto.BoardCommentItemDto;
import dev40.querydsl.board.model.BoardCommentDto.BoardCommentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BoardCommentService {
    private final BoardCommentRepository boardCommentRepository;

    public Page<BoardCommentItemDto> pageComment(int articleSeq, Pageable pageable) {
        Page<BoardCommentItemDto> pageResult = boardCommentRepository.pageComment(articleSeq, pageable);
        return pageResult;
    }

    @Transactional
    public BoardCommentItemDto createComment(BoardCommentItemDto comment) {
        BoardCommentEntity commentEntity = BoardCommentEntity.of(comment);
        boardCommentRepository.save(commentEntity);
        return BoardCommentItemDto.of(commentEntity);
    }

    @Transactional
    public BoardCommentItemDto updateComment(BoardCommentUpdateDto updateComment) {
        BoardCommentEntity comment = boardCommentRepository.findById(updateComment.getCommentSeq()).orElseThrow();
        comment.update(updateComment);
        return BoardCommentItemDto.of(comment);
    }

    @Transactional
    public void deleteComment(int commentSeq) {
        BoardCommentEntity comment = boardCommentRepository.findById(commentSeq).orElseThrow();
        comment.applyDelete();
    }

}
