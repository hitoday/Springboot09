package com.example.petpj.service.textTransfer;

import java.util.List;

import com.example.petpj.Entity.data.ScrapingEntity;
import com.example.petpj.repository.data.ScrapingEntityRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//selenim : 동적인 데이터 수집 가능(직접 브라우저를 통제해 사람처럼 브라우저 작동을 해 데이터 수집) : chromeDriver
//jsoup : httpRequest를 사용해서 정적인 데이터(HTML, Css)를 수집
//0830
@Service
public class Selenium {

    //selenum 드라이버 다운
    private WebDriver driver;
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH ="C:/Users/admin/Desktop/PetPj/src/main/resources/static/tool/chromedriver.exe";
    //매서드 매개변수로 받아서 스크래핑 동작을 위한 변수 선언
    private String base_url;

    @Autowired
    private ScrapingEntityRepository scrapingEntityRepo;

    public void scraping() {


        //System.io : 개발한 자바 프로그램(런타임)에서 외부 프로그램을 작동하기 위한 객체
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver();
        //scraping 해올 url 주소 입력
        base_url = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13";
        driver.get(base_url);

        try {
            Thread.sleep(5000);
            //스크래핑할 페이지의 전체 데이터 출력
//            System.out.println(driver.getPageSource());
            //WebElement는 HTML에서 하나의 튜플이라 보면 된다.
//            WebElement element = driver.findElement(By.tagName("button"));
//            List<WebElement> elements_span = driver.findElements(By.tagName("span"));
//
//            int checkNum = 0;
//            for(WebElement e : elements_span) {
//                System.out.println(checkNum);
//                System.out.println(e.getText());
//                checkNum++;
//            }
            //스크래핑할 페이지의 전체 데이터 출력
//            System.out.println(driver.getPageSource());

        //tag search
        List<WebElement> elements_button = driver.findElements(By.tagName("button"));
        int checkNum = 0;
        //아래 for문에서는 button tag를 가져온다.
        for(WebElement e : elements_button){
            //button tag 안에있는 span tage를 가져온다
            List<WebElement> elements_span = e.findElements(By.tagName("span"));
            //Entity를 인스턴스를 만듬
            ScrapingEntity scrapingEntity = new ScrapingEntity();

            checkNum = 0;
            //아래 for문에서는 span tag를 가져온다.
            for(WebElement e_span : elements_span){
                if(!e_span.getText().equals("")){
                    System.out.println(checkNum);
                    System.out.println(e_span.getText());
                    System.out.println(e_span.getTagName());

                    //Entity에 스크래핑을 통해 얻은 데이터를 넣기
                    if(checkNum == 0){
                        scrapingEntity.setCityName(e_span.getText());
                    } else if (checkNum == 1) {
                        scrapingEntity.setConfirmed(e_span.getText());
                    } else if (checkNum == 2) {
                        scrapingEntity.setIncrease_num(e_span.getText());
                    }
                    checkNum++;
                }
            }
            //crudRepo 엔티티 저장
//            if (!scrapingEntity.getCityName().equals("")) {
                scrapingEntityRepo.save(scrapingEntity);
//            }
        }

        //css 출력
//        List<WebElement> elements_canvas = driver.findElements(By.tagName("canvas"));
//        for(WebElement e : elements_canvas){
//            System.out.println(e.getCssValue("width"));
//        }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //공식문서에서는 close()가 아니라 quit()를 권장한다.
            driver.quit();
        }
    }
}
