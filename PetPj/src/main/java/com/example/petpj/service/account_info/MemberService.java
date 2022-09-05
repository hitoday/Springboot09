package com.example.petpj.service.account_info;

import com.example.petpj.Entity.account_info.Member;

import java.util.List;

public interface MemberService {

    List<Member> getMemberList(Member member);

    void insertMember(Member member);

    void updateAccount(Member member);

    void deleteAccount(Member member);

    Member getAccount(Member member);

    //Email 또는 ID를 조회하여 튜플을 찾기위해 사용
    List<Member> getMemberWhereIdOrEmail(String Id);

//    //일부분만 검색해 사용유저 찾기(3조)
//    //결과값 : 입력받은 정보(email. id, pw)가 유사 사실 유뮤 확인 후 비밀번호 변경
//    //(updateMember의 password)
//
//    boolean booleanSearchUserByEmail(Member member);
//
//    boolean booleanSearchUserById(Member member);
//
//    boolean booleanSearchUserByPassword(Member member);
//
//
//    // (6조)***별표 처리 MemberList (replace..)
//    List<Member> getMemberListEmailSecurityStarByMemberList(List<Member> memberlist);
//
//    //(6조)민감데이터(SHA256...)
//    List<Member> getMemberListEncodingByMemberList(List<Member> memberlist);
//
//    //작성자의 모든 게시글 출력
//    List<Member> getBoardListAllBoardListByMemberId(Member member);
//
//    //board의 작성자와 회원이 같은지 확인
//    boolean booleanMemberIdEqualsBoardWriterByMember(Member member);
//
//    //키워드 분석
//

 }
