package dev40.querydsl.board.controller;

import dev40.querydsl.board.model.BoardCommentDto.BoardCommentItemDto;
import dev40.querydsl.board.model.BoardCommentDto.BoardCommentUpdateDto;
import dev40.querydsl.board.service.BoardCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Api(tags = {"댓글 API"})
@Validated
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @ApiOperation(value = "댓글 페이징")
    @ApiImplicitParam(name = "articleSeq", value = "게시물 아이디", required = true, paramType = "path")
    @GetMapping("/article/{articleSeq}/comments")
    public ResponseEntity<Page<BoardCommentItemDto>> pageComment(@PathVariable("articleSeq") @Positive int articleSeq, @Valid Pageable pageable) {
        Page<BoardCommentItemDto> pageResult = boardCommentService.pageComment(articleSeq, pageable);
        return ResponseEntity.ok(pageResult);
    }

    @ApiOperation(value = "댓글 등록")
    @PostMapping("/article/comment")
    public ResponseEntity<BoardCommentItemDto> createComment(@Valid BoardCommentItemDto comment) {
        BoardCommentItemDto boardComment = boardCommentService.createComment(comment);
        return ResponseEntity.ok(boardComment);
    }

    @ApiOperation(value = "댓글 수정")
    @PutMapping("/comment/{commentSeq}")
    public ResponseEntity<BoardCommentItemDto> updateComment(@PathVariable("commentSeq") @Positive int commentSeq, @Valid BoardCommentUpdateDto updateComment) {
        updateComment.setCommentSeq(commentSeq);
        BoardCommentItemDto boardComment = boardCommentService.updateComment(updateComment);
        return ResponseEntity.ok(boardComment);
    }

    @ApiOperation(value = "댓글 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentSeq", value = "댓글 아이디", required = true, paramType = "path"),
    })
    @DeleteMapping("/article/comment/{commentSeq}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentSeq") @Positive int commentSeq) {
        boardCommentService.deleteComment(commentSeq);
        return ResponseEntity.ok().build();
    }
}
