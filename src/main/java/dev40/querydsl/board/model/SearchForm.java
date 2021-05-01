package dev40.querydsl.board.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class SearchForm {
    /**
     * 제목 검색어
     */
    @Length(max = 100)
    private String title;

    /**
     * 본문 검색어
     */
    @Length(max = 100)
    private String content;
}
