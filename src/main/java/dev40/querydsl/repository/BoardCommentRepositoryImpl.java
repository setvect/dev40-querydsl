package dev40.querydsl.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev40.querydsl.board.entity.QBoardCommentEntity;
import dev40.querydsl.board.model.BoardCommentDto.BoardCommentItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardCommentRepositoryImpl implements BoardCommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public Page<BoardCommentItemDto> pageComment(int articleSeq, Pageable pageable) {
        QueryResults<BoardCommentItemDto> result = queryFactory.from(QBoardCommentEntity.boardCommentEntity).select(Projections.fields(BoardCommentItemDto.class,
                QBoardCommentEntity.boardCommentEntity.commentSeq,
                QBoardCommentEntity.boardCommentEntity.userName,
                QBoardCommentEntity.boardCommentEntity.content,
                QBoardCommentEntity.boardCommentEntity.regDate
        ))
                .where(
                        QBoardCommentEntity.boardCommentEntity.deleteF.eq(false),
                        QBoardCommentEntity.boardCommentEntity.boardArticle.articleSeq.eq(articleSeq)
                )
                .orderBy(QBoardCommentEntity.boardCommentEntity.commentSeq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
