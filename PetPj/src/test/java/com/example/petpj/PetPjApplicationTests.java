package com.example.petpj;

import com.example.petpj.Entity.account_info.Member;
import com.example.petpj.Entity.board.Board;
import com.example.petpj.repository.account_info.MemberRepository;
import com.example.petpj.repository.board.BoardRepository;
import com.example.petpj.service.textTransfer.Selenium;
import com.example.petpj.service.textTransfer.scWeather;
import com.example.petpj.service.textTransfer.TextTransfer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class PetPjApplicationTests {

    private final MemberRepository memberRepo;

    private final BoardRepository boardRepo;

    private final TextTransfer textTransfer;

    private final Selenium selenium;

    @Autowired
    protected PetPjApplicationTests(MemberRepository memberRepo, TextTransfer textTransfer, Selenium selenium,
                                    BoardRepository boardRepo){
        this.memberRepo = memberRepo;
        this.boardRepo = boardRepo;
        this.textTransfer = textTransfer;
        this.selenium = selenium;
    }

//    0829
//    member테이블에 id, 비번, email데이터가 잘 들어가는지 테스트
//    @Test
//    //아래 Test Results에 나오는 테스트 클래스, 메소드 이름을 정할 수 있는 @
//    @DisplayName("저장, 데이터가 잘 들어갔는지 확인")
//    void contextLoads() {
//        //Setter로 엔티티를 생성하고 repositoy가 정상 작동하는지 확인
//        Member member = new Member();
//        member.setId("jumanClass5");
//        member.setPassword("12345@");
//        member.setEmail("class@naver.com");
//        member.setCreateDate(new Date());
//        memberRepo.save(member);
//    }
    //0829
    @Test
    void textTest() throws Exception {
        textTransfer.transferText3Word("abc567@naver.com");
    }
    //0830

    @Test
    void Scraping() {
        selenium.scraping();
    }
    @Autowired
    scWeather seleniumWeather;

    @Test
    void WeatherSc() {seleniumWeather.WeatherSc();}

    @Test
    void findByIdLike() {
        memberRepo.findByIdLike("kim");
    }

//    @Test
//    void findByIdLik2() {
//        memberRepo.findByIdLike2("kim2");
//    }

    // 회원가입 및 게시글 입력을 위한 테스트 입니다.
    @Test
    void contextSave() {
        /*회원가입을 위한 Member Entity가 생성이 되고
         Column에 Id, Password, Email 값이 잘 들어가는지 테스트 합니다.*/
        Member member = new Member();
        member.setId("kim123");
        member.setPassword("123456");
        member.setEmail("kim319@naver.com");
        memberRepo.save(member);

        /*게시글 입력을 위한 Board Entity가 생성이 되고
         Column에 입력시간, writer, title, comments 값이 잘 들어가는지 테스트 합니다.*/
        Board board = new Board();
        board.setCreateDate(new Date());
        board.setWriter("김명훈");
        board.setTitle("회원가입 인사");
        board.setComments("안녕하세요 잘 부탁드립니다");
        boardRepo.save(board);
    }
}
