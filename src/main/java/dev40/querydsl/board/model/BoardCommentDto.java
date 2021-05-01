package dev40.querydsl.board.model;

import dev40.querydsl.board.entity.BoardCommentEntity;
import dev40.querydsl.common.AppUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


public class BoardCommentDto {
    @Getter
    @Setter
    public static class BoardCommentItemDto {
        @ApiModelProperty(hidden = true)
        private int commentSeq;

        private int articleSeq;

        @NotEmpty
        @Length(min = 1, max = 20)
        private String userName;

        @NotEmpty
        @Length(min = 1, max = 2000)
        private String content;

        @ApiModelProperty(hidden = true)
        private LocalDateTime regDate;

        public static BoardCommentItemDto of(BoardCommentEntity commentEntity) {
            BoardCommentItemDto comment = AppUtil.getMapper().map(commentEntity, BoardCommentItemDto.class);
            comment.setArticleSeq(commentEntity.getCommentSeq());
            return comment;
        }
    }

    @Getter
    @Setter
    public static class BoardCommentUpdateDto {
        @ApiModelProperty(hidden = true)
        private int commentSeq;

        @Length(min = 1, max = 20)
        private String userName;

        @Length(min = 1, max = 2000)
        private String content;
    }
}
