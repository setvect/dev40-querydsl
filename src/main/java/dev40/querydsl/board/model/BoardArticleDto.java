package dev40.querydsl.board.model;

import dev40.querydsl.board.entity.BoardArticleEntity;
import dev40.querydsl.common.AppUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter

public class BoardArticleDto {
    @Getter
    @Setter
    public static class BoardArticleItemDto {
        @ApiModelProperty(hidden = true)
        private int articleSeq;

        @ApiModelProperty(required = true, value = "제목")
        @NotEmpty
        @Length(min = 1, max = 100)
        private String title;

        @ApiModelProperty(required = true, value = "내용")
        @NotEmpty
        private String content;

        @ApiModelProperty(required = true, value = "작성자 이름")
        @Length(min = 1, max = 20)
        private String userName;

        @ApiModelProperty(hidden = true)
        private LocalDateTime regDate;

        @ApiModelProperty(hidden = true)
        private long commentCount;

        public static BoardArticleItemDto of(BoardArticleEntity boardArticleEntity) {
            return AppUtil.getMapper().map(boardArticleEntity, BoardArticleItemDto.class);
        }
    }

    @Getter
    @Setter
    public static class BoardArticleUpdateDto {
        @ApiModelProperty(hidden = true)
        private int articleSeq;

        @Length(min = 1, max = 100)
        private String title;

        @NotEmpty
        private String content;

        @Length(min = 1, max = 20)
        private String userName;
    }

}
