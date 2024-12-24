package com.example.handy_home.domain.use_cases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SeleniumUseCase {

    static private String WEB_DRIVER_ID = "webdriver.chrome.driver";
    static private String WEB_DRIVER_PATH = "static/chromedriver.exe";

    public List<String> searchKeywordInNaverRealty(String keyword) {
        System.setProperty(WEB_DRIVER_ID, Objects.requireNonNull(getClass().getClassLoader().getResource(WEB_DRIVER_PATH)).getPath());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("headless");

        WebDriver driver = new ChromeDriver(options);
    }

}
