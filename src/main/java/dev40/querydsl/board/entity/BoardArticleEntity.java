package dev40.querydsl.board.entity;

import dev40.querydsl.board.model.BoardArticleDto.BoardArticleItemDto;
import dev40.querydsl.board.model.BoardArticleDto.BoardArticleUpdateDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "BOARD_ARTICLE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class BoardArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTICLE_SEQ", nullable = false)
    private int articleSeq;

    @Column(name = "USER_NAME", length = 50, nullable = false)
    private String userName;

    @Column(name = "TITLE", length = 200, nullable = false)
    private String title;

    @Column(name = "CONTENT", nullable = false)
    @Lob
    private String content;

    @Column(name = "REG_DATE", nullable = false)
    @CreatedDate
    private LocalDateTime regDate;

    @Column(name = "DELETE_F", nullable = false)
    @Type(type = "yes_no")
    private boolean deleteF;

    @OneToMany(mappedBy = "boardArticle")
    private List<BoardCommentEntity> comments;

    public static BoardArticleEntity of(BoardArticleItemDto article) {
        BoardArticleEntity boardArticleEntity = new BoardArticleEntity();
        boardArticleEntity.title = article.getTitle();
        boardArticleEntity.content = article.getContent();
        boardArticleEntity.userName = article.getUserName();
        return boardArticleEntity;
    }

    public static BoardArticleEntity of(int articleSeq) {
        BoardArticleEntity article = new BoardArticleEntity();
        article.articleSeq = articleSeq;
        return article;
    }

    public void update(BoardArticleUpdateDto updateArticle) {
        this.title = updateArticle.getTitle();
        this.content = updateArticle.getContent();
        this.userName = updateArticle.getUserName();
    }

    public void applyDelete() {
        this.deleteF = true;
    }
}
