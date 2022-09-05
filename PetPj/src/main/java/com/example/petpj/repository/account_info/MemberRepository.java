package com.example.petpj.repository.account_info;

import com.example.petpj.Entity.account_info.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//멤버 레파지토리는 크루드레파지토리 를 상속받아 기능을 온전히 사용
//크루드 레파지토리 란??
//DB에 기본적인 SQL문을 통해 소통한다(INSERT INTO, SELECT, UPDATE, DELETE)
public interface MemberRepository extends JpaRepository<Member, Long> {

    //0817
//    @Query(value = "select n from Member n where n.email = :email_1 or n.id = :id_1")
//    List<Member> findMemberByEmailOrId(String Email_1, String Id_1);

    @Query(value = "select m from Member m where m.id like %?1%")
    List<Member> findByIdLike(String id);

//    @Query(value = "SELECT m.id FROM Member m where m.id = :%im%", nativeQuery = true)
//    List<Member> findByIdLike(String Id);

//    @Query(value = "SELECT m.id FROM Member where m.id like %:i%", nativeQuery = true)
//    List<Member> findByIdLike2(String id);

    //(ID는 중복가능한 구조에서) Id값을 매개변수로 넣고, 아이디 생성날짜가 가장 최신인것
//    @Query(value = "select n from Member n where n.id = :id order by n.createDate DESC")
//    Member findFirstById(String Id);
}
