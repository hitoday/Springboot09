package com.example.petpj;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
//Entity의 @CreatedDate, @LastModifiedDate를 자동으로 Date값을 주입한다
//위에 @ 사용이유는??
//Date를 주입하거나 설정하는 부분이 3가지 있는데
//1. 클라이언트 : 사용자가 임의로 날짜를 수정 할 수 있는 위험이 있다
//이미 클라이언트에서 Date정보를 전달받으면 쉽게 Entitiy에 데이터 입력이 가능하다
//2. 서버에서 : 클라이언트가 접속하는 서버의 날짜 기준으로 일관성 있음
//서버에서 날짜 내장메서드를 실행하는 리소스 문제가 있다
//3. DB에서 : DB는 모든 정보를 총괄하는 1개 뿐인 서버 (날짜를 완전히 일관성)
//모든 백엔드가 접속하기 때문에 리소스 문제를 야기할 가능성이 높다
@EnableJpaAuditing
@SpringBootApplication
public class  PetPjApplication {

    public static void main(String[] args) {
        SpringApplication application =
                new SpringApplication(PetPjApplication.class);
//        application.setBannerMode(Banner.Mode.OFF);
        SpringApplication.run(PetPjApplication.class, args);
    }

}
