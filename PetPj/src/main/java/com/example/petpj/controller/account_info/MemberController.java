package com.example.petpj.controller.account_info;

import com.example.petpj.Entity.account_info.Member;
import com.example.petpj.service.account_info.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

//어노테이션 컨트롤러를 선언하면 디스페처 서블릿이 컨트롤러를 찾는다.
@Controller
public class MemberController {

    //0818
    //컨트롤러 클래스가 실행되면 MemberService를 불러와서 주입 당하는것
    @Autowired
    private MemberService memberService;

//    @Autowired
//    protected MemberController(MemberService memberService){
//        this.memberService = memberService;
//    }

//    return 타입이 String인 이유는?? HTML 파일명을 찾기 위해서
    @GetMapping("account/insertAccount")
    public String insertMember(){
        System.out.println("----------GET insertAccount");
        return "account/insertAccount";
    }
    //Member 라는 매개변수로 controller에 전달
    //Member(Entity)이고 DTO(Data Transfer Object)
    //어디서 받거나 만든 데이터를 객체로 만드는 것
    @PostMapping("account/insertAccount")
    public String insertMember(Member member){
        System.out.println("----------POST insertAccount");
            member.setCreateDate(new Date());
            member.setUpdateDate(new Date());
            memberService.insertMember(member);
            return "redirect:getMemberList";
    }
        // 0812
    @GetMapping("account/getMemberList")
    public String getMemberList(Model model, Member member){
        System.out.println("----------GET getMemberList");
        model.addAttribute("memberList", memberService.getMemberList(member));
        return "account/getMemberList";
    }

    @GetMapping("account/getAccount")
    public String getAccount(Member member, Model model){
        System.out.println("----------GET getAccount");
        model.addAttribute("member", memberService.getAccount(member));
        return "account/getAccount";
    }

    @PostMapping("account/updateAccount")
    public String updateAccount(Member member){
        System.out.println("----------POST updateAccount");
        memberService.updateAccount(member);
        return "redirect:getMemberList";
    }

    @PostMapping("account/deleteAccount")
    public String deleteAccount(Member member){
        System.out.println("----------POST deleteAccount");
        memberService.deleteAccount(member);
        return "redirect:getMemberList";
    }
    //0817
    @GetMapping("account/selectAccount")
    public String selectAccount(){
        System.out.println("----------GET selectAccount");
        return "account/selectAccount";
    }
    //0817
//    @PostMapping("account/selectAccount")
//    public String resultAccount(Member member, Model model){
//        model.addAttribute("memberL", memberService.getMemberWhereId(member.getId()));
//                return "/account/resultAccount";
//    }

    @RequestMapping(value = "account/selectAccount", method = RequestMethod.POST)
    public String selectAccount(@RequestParam("id")String id, Member member, Model model){
        System.out.println("----------Request selectAccount");
        model.addAttribute("id", id);
        model.addAttribute("member", memberService.getMemberWhereIdOrEmail(member.getId()));
        return "account/resultAccount";
    }

//    @RequestMapping("account/getMemberList")
//    public String getMemberList(Model model) {
//        List<Member> memberList = memberService.getMemberList();
//        model.addAttribute("memberList", memberList);
//
//        //디서패처 서블릿이 뷰 리저버가 찾아서 연결해 준다.
//        //viewResolver 가 같은 name의 값을 찾아준다
//        //return값이 String 인 이유는 뷰 리저버가 html파일을 찾기 위한 문자열을 리턴.
//        return "account/getMemberList";
//    }
}


