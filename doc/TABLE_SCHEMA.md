게시판 테이블 설계서
=========================

- BOARD_ARTICLE: 게시물

| Column Name | Attribute Name | Key | Type       | Len | Not Null | Description |
| ----------- | -------------- | --- | ---------- | --- | -------- | ----------- |
| ARTICLE_SEQ | 키             | PK  | INTEGER    | 4   | Y        |             |
| USER_NAME   | 이름           |     | VARCHAR    | 50  | Y        |             |
| TITLE       | 제목           |     | VARCHAR    | 200 | Y        |             |
| CONTENT     | 본문           |     | TEXT(CLOB) |     | Y        |             |
| REG_DATE    | 등록일         |     | DATETIME   |     | Y        |             |
| DELETE_F    | 삭제여부       |     | VARCHAR    | 1   | Y        | Y, N        |

- BOARD_COMMENT: 댓글

| Column Name | Attribute Name | Key | Type     | Len  | Not Null | Description   |
| ----------- | -------------- | --- | -------- | ---- | -------- | ------------- |
| COMMENT_SEQ | 일련번호       | PK  | INTEGER  | 4    | Y        |               |
| ARTICLE_SEQ | 외래키         | FK  | INTEGER  | 4    | Y        | BOARD_ARTICLE |
| USER_NAME   | 이름           |     | VARCHAR  | 50   | Y        |               |
| CONTENT     | 내용           |     | VARCHAR  | 4000 | Y        |               |
| REG_DATE    | 등록일         |     | DATETIME |      | Y        |               |
| DELETE_F    | 삭제여부       |     | VARCHAR  | 1    | Y        | Y, N          |
