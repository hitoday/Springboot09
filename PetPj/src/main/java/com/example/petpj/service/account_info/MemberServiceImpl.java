package com.example.petpj.service.account_info;

import com.example.petpj.Entity.account_info.Member;
import com.example.petpj.repository.account_info.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepo;

    //모든 회원의 정보를 가져다 오는것
    //return List<member> : 모든 회원의 정보를 List배열에 담아서 return
    //public : 모두 공개하는 매서드
    //List<Member> : 이 매서드가 실행되면 return 되는 타입
    //(List<Member>) : 뒤에 있는 결과값을 형변환
    //memberRepo : @Autowired MemberRepo를 통해 기능을 실행
    @Override
    public List<Member> getMemberList(Member member) {
        System.out.println("-----service getMemberList");
        return (List<Member>) memberRepo.findAll();
    }
    //회원 1명의 정보를 Entitiy에 맞춰서 DB에 저장한다다
   @Override
    public void insertMember(Member member) {
       System.out.println("-----service insertMember");
        memberRepo.save(member);
    }

    @Override
    public Member getAccount(Member member){
        System.out.println("-----service getAccount");
        return memberRepo.findById(member.getSeq()).get();
    }

    //0816
    //List<Member> : 리턴타입은 List 속성은 Member
    //return memberRepository의 findMemberByEmailOrId 메서드를 실행한 리턴 데이터
    @Override
    public void updateAccount(Member member){
        System.out.println("-----service updateAccount");
        /*1. seq를 통해서 튜플 정보를 모두 가져오기
        * 2. 가져온 튜플 정보 중 수정할 내용 적용
        * 3. DB에 저장(덮어쓰기)
        * findById().get() : 고유키 기준으로 튜플 전체 데이터 가져오기*/
        Member findMember = memberRepo.findById(member.getSeq()).get();
        //튜플 전체 내용 중에 ID/email주소 수정(setter)
        findMember.setId(member.getId());
        findMember.setPassword(member.getPassword());
        findMember.setEmail(member.getEmail());
        memberRepo.save(findMember);
    }

    @Override
    public void deleteAccount(Member member) {
        System.out.println("-----service deleteAccount");
        memberRepo.deleteById(member.getSeq());
    }

    @Override
    public List<Member> getMemberWhereIdOrEmail(String Id) {
        System.out.println("-----service getMemberWhereId");
        return memberRepo.findByIdLike(Id);
    }

    /*고유키
    * 1. 다른 튜플을 식별할 수 있는 값(데이터 한줄) : DB 관점에서 보는것
    * 2. 다른 테이블의 튜플과 연동하기 위한 값 (Join, 외래키) : DB관점
    * 3. 객체지향 방법으로 DB를 저장
    * 3-1. 영속성 :
    * 3-2. 고립성 : 다른 트랜젝션 작업에 연관되지 않도록 해주는 것
    * 3-2 : 관리자1은 seq 10의 회원정보를 수정했다. 이미 접속해 있던 관리자2가 seq10 회원 정보를 조회 후 수정.
    * seq10의 회원정보를 바꾸는 작업이 한 개의 트랜젝션이다.
    * 관리자2의 seq10 회원의 정보를 조회하고 수정하는 작업도 한 개의 트렌젝션 이다.
    * 관리자1의 트랜젝션 작업이 완료될 때까지 관리자2는 seq10회원정보는 이전 정보를 조회하고 있고
    * 관리자1의 트랜젝션 작업이 완료되는 순간까지 관리자2는 seq10회원정보를 수정 할 수 없다.
    * 다른 필드값은 수정이 가능해도, seq는 튜플의 식별자로써 수정이 불가해야, 관리자1,2의 트랜젝션 작업을
    * 스프링부트에서 구분 할 수 있다.*/
//    @Override
//    public void deleteMember(Member member){
//        memberRepo.deleteById(member.getSeq());
//    }
}
