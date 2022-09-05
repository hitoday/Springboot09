package com.example.petpj.service.textTransfer;

import com.example.petpj.Entity.data.ScrapingWeather;
import com.example.petpj.repository.data.ScrapingWeatherRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class scWeather {

    private WebDriver driver;
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "C:/Users/admin/Desktop/PetPj/src/main/resources/static/tool/chromedriver.exe";
    private String base_url;

    @Autowired
    private ScrapingWeatherRepository scrapingWeatherRepo;

    public void WeatherSc() {

        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver();
        base_url = "http://txbus.t-money.co.kr/useinf/trmlInfP.do";
        driver.get(base_url);

        try {
            Thread.sleep(7000);

            List<WebElement> elements_td = driver.findElements(By.tagName("td"));
            int scNum = 0;

            for(WebElement e : elements_td){
                List<WebElement> elements_div = e.findElements(By.tagName("div"));

                ScrapingWeather scrapingWeather = new ScrapingWeather();

                scNum = 0;
                for(WebElement e_div : elements_div){
                    if(!e_div.getText().equals("")){
                        System.out.println(scNum);
                        System.out.println(e_div.getText());
                        System.out.println(e_div.getTagName());

                        if(scNum == 0){
                            scrapingWeather.setHour(e_div.getText());
                        } else if (scNum == 1) {
                            scrapingWeather.setWeather(e_div.getText());
                        }else if (scNum == 2) {
                            scrapingWeather.setTemp(e_div.getText());
                        }else if (scNum == 3) {
                            scrapingWeather.setSensible_temp(e_div.getText());
                        }
                        scNum++;
                    }
//                }if (!scrapingWeather.getWeather().equals("")) {
                    scrapingWeatherRepo.save(scrapingWeather);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }
}