package dev40.querydsl.board.service;

import dev40.querydsl.board.entity.BoardArticleEntity;
import dev40.querydsl.repository.BoardArticleRepository;
import dev40.querydsl.board.model.BoardArticleDto.BoardArticleItemDto;
import dev40.querydsl.board.model.BoardArticleDto.BoardArticleUpdateDto;
import dev40.querydsl.board.model.SearchForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BoardArticleService {
    private final BoardArticleRepository boardArticleRepository;

    public Page<BoardArticleItemDto> pageArticle(SearchForm search, Pageable pageable) {
        Page<BoardArticleItemDto> pageResult = boardArticleRepository.pageArticle(search, pageable);
        return pageResult;
    }

    public BoardArticleItemDto getArticle(int articleSeq) {
        BoardArticleEntity article = boardArticleRepository.findById(articleSeq).orElseThrow();
        if(article.isDeleteF()){
            throw new RuntimeException("삭제된 게시물 입니다.");
        }
        return BoardArticleItemDto.of(article);
    }

    @Transactional
    public BoardArticleItemDto createArticle(BoardArticleItemDto article) {
        BoardArticleEntity articleEntity = BoardArticleEntity.of(article);
        boardArticleRepository.save(articleEntity);
        return BoardArticleItemDto.of(articleEntity);
    }

    @Transactional
    public BoardArticleItemDto updateArticle(BoardArticleUpdateDto updateArticle) {
        BoardArticleEntity article = boardArticleRepository.findById(updateArticle.getArticleSeq()).orElseThrow();
        article.update(updateArticle);
        return BoardArticleItemDto.of(article);
    }

    @Transactional
    public void deleteArticle(int articleSeq) {
        BoardArticleEntity article = boardArticleRepository.findById(articleSeq).orElseThrow();
        article.applyDelete();
    }

}
