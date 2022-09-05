package com.example.petpj.Entity.board;

import java.io.Serializable;
import java.util.Date;

//lombok이라는 외장 라이브러리를 사용 하는것
import com.example.petpj.Entity.account_info.Member;
import com.example.petpj.Entity.base.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

// 아래 멤버변수들(Long seq, String title...)을 적용한 어노테이션을 사용할 수 있게 된다.
@Getter
@Setter
@ToString
@Entity
public class Board extends BaseTimeEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String category;

    @Column(length = 40)
    private String title;

    @Column(updatable = false)
    private String writer;

    @Column
    @ColumnDefault("'no content'")
    private String content;

    @Column
    private String writerC;

    @Column
    private String comments;

    @Column(insertable = false, updatable = false)
    private Long cnt;

    // 0822
    // @ManyToOne 다양한 board는 1개의 member를 바라본다
    // Member를 필드에 선언
    // 참조키가 어디인지 선언을 해줘야 한다(member 기본키가 board의 참조키로 기본적으로 할당)
    // Board클래스의 writer필드는 Member 클래스의 id필드와 연관돼 있고, 참조키 id로 연결 돼 있다.
    @ManyToOne
    @JoinColumn(name = "member", referencedColumnName = "member_id")
    private Member member;
}
