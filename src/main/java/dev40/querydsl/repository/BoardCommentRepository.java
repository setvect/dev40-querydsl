package dev40.querydsl.repository;

import dev40.querydsl.board.entity.BoardCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardCommentEntity, Integer>, BoardCommentRepositoryCustom {

}
