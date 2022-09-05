package com.example.petpj.repository.board;

/**
 * @package : com.example.demo.persistence
 * @name : BoardRepository.java
 * @date : 2022-08-08 오후 6:20
 * @author : Rubisco
 * @version : 1.0.0
 * @modifyed :
 * @description : 게시판 레포지토리
 **/

import com.example.petpj.Entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//CrudRepository를 상속받음
//CrudRepository : Entity 를 매개체로 create, read, update, delete 기능을 하는 인터페이스
//CrudRepository<Board, Long>의 매개변수 Board(Entity)와 Long(PK기본키의 타입)을 선언
//JPA가 어떤 객체로 어떤 타입으로 찾아야하는지 알아 들음


public interface BoardRepository extends JpaRepository<Board, Long> {

    // 0822
    // 회원 id를 검색하면(writer필드 와 Id가 동일) 관련된 writer 필드의 게시글 모두를 출력받아 리턴한다.
    // inner Join : ANSI QUERY 이다
    // board의 튜플을 가져와야 하기 때문에 FROM Board(Board 테이블이 기준)
    @Query(value = "SELECT b FROM Board b INNER JOIN Member m ON b.writer = m.id WHERE m.id = :memberId")
    List<Board> findAllByMemberIdEqualsBoardWriter(String memberId);
}
