package com.example.petpj.service.textTransfer;

import org.springframework.stereotype.Service;

@Service
public class TextTransfer {

    public String transferText3Word(String text) throws Exception {

        String wordFirst3 = text.substring(0, 3);
        System.out.println("앞의 3글자 = " + wordFirst3);
        String wordLast = text.substring(4, text.length());
        System.out.println("뒤의 나머지 글자 = " + wordLast);
        wordLast = wordLast.replaceAll(".", "*");
        System.out.println("앞의 3글자 뒤 나머지 * 처리된 값은? " + wordFirst3 + wordLast + " 입니다");

        return wordFirst3 + wordLast;
    }
}
//    public String transferText3Word(String text) throws Exception {
//
//        //java 문자열 치환 내장메서드 : split, subString..
//        String wordFirst3 = text.substring(0,3);
//        System.out.println("앞의 3글자 = " +wordFirst3);
//        String wordLast = text.substring(4, text.length());
//        System.out.println("뒤의 나머지 글자 = "+ wordLast);
//        //substring과 split 구조 차이
//        //subString : 원본 배열을 참조해서 순번과 길이만 가지고 자름(객체를 따로 생성해서 관리 X)
//        //split :  새로운 인스턴스를 만들어서 문자열을 자르고, 더불어 결과값을 String배열로 받아옴(객체를 따로 생성해서 관리)
//
//        //replaceAll 매서드의 변경 할 값에 "."을 사용하면 모든 문자열을 지정하는 것
//        wordLast = wordLast.replaceAll(".", "*");
////        wordLast = wordLast.replaceAll("<", "&lt;");
////        wordLast = wordLast.replaceAll(">", "&gt;");
//        System.out.println(wordFirst3);
//        System.out.println(wordLast);
//        System.out.println(wordFirst3 + wordLast);
//
//        return wordFirst3 + wordLast;
//    }


