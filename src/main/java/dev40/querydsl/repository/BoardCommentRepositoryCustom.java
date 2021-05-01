package dev40.querydsl.repository;

import dev40.querydsl.board.model.BoardCommentDto.BoardCommentItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardCommentRepositoryCustom {
    Page<BoardCommentItemDto> pageComment(int articleSeq, Pageable pageable);
}
