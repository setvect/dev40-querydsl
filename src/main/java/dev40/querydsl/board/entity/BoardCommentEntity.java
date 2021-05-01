package dev40.querydsl.board.entity;

import dev40.querydsl.board.model.BoardCommentDto.BoardCommentItemDto;
import dev40.querydsl.board.model.BoardCommentDto.BoardCommentUpdateDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity(name = "BOARD_COMMENT")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class BoardCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_SEQ", nullable = true)
    private int commentSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTICLE_SEQ")
    private BoardArticleEntity boardArticle;

    @Column(name = "USER_NAME", length = 50, nullable = true)
    private String userName;

    @Column(name = "CONTENT", length = 4000, nullable = true)
    private String content;

    @Column(name = "REG_DATE", nullable = true)
    @CreatedDate
    private LocalDateTime regDate;

    @Column(name = "DELETE_F", nullable = true)
    @Type(type = "yes_no")
    private boolean deleteF;

    public static BoardCommentEntity of(BoardCommentItemDto boardCommentDto) {
        BoardCommentEntity entity = new BoardCommentEntity();
        entity.content = boardCommentDto.getContent();
        entity.userName = boardCommentDto.getUserName();
        entity.setBoardArticle(BoardArticleEntity.of(boardCommentDto.getArticleSeq()));
        return entity;
    }

    public void update(BoardCommentUpdateDto updateComment) {
        this.content = updateComment.getContent();
        this.userName = updateComment.getUserName();
    }

    public void applyDelete() {
        this.deleteF = true;
    }
}
