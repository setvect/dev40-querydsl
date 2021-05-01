package dev40.querydsl.repository;

import dev40.querydsl.board.entity.BoardArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardArticleRepository extends JpaRepository<BoardArticleEntity, Integer>, BoardArticleRepositoryCustom {

}
