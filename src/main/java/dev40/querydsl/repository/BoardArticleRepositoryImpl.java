package dev40.querydsl.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev40.querydsl.board.entity.QBoardArticleEntity;
import dev40.querydsl.board.entity.QBoardCommentEntity;
import dev40.querydsl.board.model.BoardArticleDto.BoardArticleItemDto;
import dev40.querydsl.board.model.SearchForm;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardArticleRepositoryImpl implements BoardArticleRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BoardArticleItemDto> pageArticle(SearchForm searchForm, Pageable pageable) {
        QueryResults<BoardArticleItemDto> result = queryFactory.from(QBoardArticleEntity.boardArticleEntity).select(Projections.fields(BoardArticleItemDto.class,
                QBoardArticleEntity.boardArticleEntity.articleSeq,
                QBoardArticleEntity.boardArticleEntity.userName,
                QBoardArticleEntity.boardArticleEntity.title,
                QBoardArticleEntity.boardArticleEntity.content,
                QBoardArticleEntity.boardArticleEntity.regDate,
                QBoardArticleEntity.boardArticleEntity.deleteF,
                ExpressionUtils.as(JPAExpressions.select(QBoardCommentEntity.boardCommentEntity.commentSeq.count())
                                .from(QBoardCommentEntity.boardCommentEntity)
                                .where(QBoardCommentEntity.boardCommentEntity.boardArticle.eq(QBoardArticleEntity.boardArticleEntity)),
                        "commentCount")))
                .where(
                        QBoardArticleEntity.boardArticleEntity.deleteF.eq(false),
                        containsTitle(searchForm.getTitle()),
                        containsContent(searchForm.getContent())
                )
                .orderBy(QBoardArticleEntity.boardArticleEntity.articleSeq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        Page<BoardArticleItemDto> pageResult = new PageImpl<>(result.getResults(), pageable, result.getTotal());
        return pageResult;
    }


    private BooleanExpression containsTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            return null;
        }
        return QBoardArticleEntity.boardArticleEntity.title.contains(title);
    }

    private BooleanExpression containsContent(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        return QBoardArticleEntity.boardArticleEntity.content.contains(content);
    }
}
