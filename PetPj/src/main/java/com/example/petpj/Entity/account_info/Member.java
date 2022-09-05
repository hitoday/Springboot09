package com.example.petpj.Entity.account_info;

import com.example.petpj.Entity.base.BaseTimeEntity;
import com.example.petpj.Entity.board.Board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//어노테이션 엔티티는 JPA가 이 객체를 기준으로 table
@ToString
@Entity
public class Member extends BaseTimeEntity implements Serializable {
    //JPA 객체에 맞춰서 SQL문으로 바꿔주는 것
    //SELECT [*컬럼명 = 객체의 필드] FROM TABLS_NAME*객체;

    @Id//테이블을 만들때, 테이블의 튜플을 식별할 수 있는 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long seq;

    // 0822 unique 키 설정해줌
    @Getter
    @Setter
    @Column(name = "member_id", nullable = false, updatable = false, unique = true)
    @Size(min = 6, max = 20)
    private String id;

    // member는 여러개의 board를 가질 수 있다고 선언,
    // board들을 가지고 있다고 필드에 넣음 (JPA는 이 필드 내용으로 테이블 연관관계(Join)으로 식별)
    // @OneToMany 는 member 하나의 튜플마다 여러개의 board를 가진다는 속성을 선언과
    // 다수의 엔티티 연동에 SpringBoot는 Serializable 상속을 요구한다.

    @OneToMany(mappedBy = "member")
    private List<Board> boardList = new ArrayList<>();

    @Getter
    @Setter
    @Column(nullable = false)
    @Size(min = 10, max = 20)
    private String password;

    @Getter
    @Setter
    @Column
    private String email;

}
