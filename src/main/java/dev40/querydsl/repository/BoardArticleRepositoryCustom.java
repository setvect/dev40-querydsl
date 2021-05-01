package dev40.querydsl.repository;

import dev40.querydsl.board.model.BoardArticleDto.BoardArticleItemDto;
import dev40.querydsl.board.model.SearchForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardArticleRepositoryCustom {
    Page<BoardArticleItemDto> pageArticle(SearchForm searchForm, Pageable pageable);
}
