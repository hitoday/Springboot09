package com.example.petpj.controller.api;

import com.example.petpj.Entity.customDto.CustomStudentData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    //0829
    //CRUD restFullApi rest하게 API를 만들자(암묵적인 규칙)
    //data를 전달하는 controller URI주소 만들기

    //@ResponseBody는 view페이지가 아닌 return 으로 바로 반환하게 해주는 @
    @ResponseBody
    @RequestMapping("/read/alldata")
    public CustomStudentData  readAlldata() {
        CustomStudentData jskim = new CustomStudentData();

        jskim.setName("김명훈");
        jskim.setGroup(1);
        jskim.setPosition("backend");

        return jskim;
    }
}
