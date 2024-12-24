package com.example.handy_home.domain.use_cases;

import org.apache.commons.logging.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SeleniumUseCase {

    static private String WEB_DRIVER_ID = "webdriver.chrome.driver";
    static private String WEB_DRIVER_PATH = "static/chromedriver";

    static private String WEB_BASE_URL = "https://new.land.naver.com/complexes?ms=37.3595704,127.105399,16&a=APT:ABYG:JGC:PRE&e=RETAIL";

    WebDriver driver;

    public SeleniumUseCase() {
        initWebDriver();
    }

    private void initWebDriver() {
        System.setProperty(WEB_DRIVER_ID, Objects.requireNonNull(getClass().getClassLoader().getResource(WEB_DRIVER_PATH)).getPath());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("headless");

        driver = new ChromeDriver(options);
        driver.get(WEB_BASE_URL);
    }

    public List<String> searchKeywordInNaverRealty(String keyword) {
        if (driver == null) {
            initWebDriver();
        }

        if (!Objects.equals(driver.getCurrentUrl(), WEB_BASE_URL)) {
            driver.get(WEB_BASE_URL);
        }

        try {
            WebElement searchInput = driver.findElement(By.id("land_search"));
            searchInput.sendKeys(keyword);
            searchInput.sendKeys(Keys.ENTER);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("list_contents")));

            List<WebElement> itemList = driver.findElements(By.cssSelector(".item_area .item"));

            final List<String> resultList = new ArrayList<>();

            for (WebElement item : itemList) {
                WebElement itemLink = item.findElement(By.cssSelector(".item_inner .item_link"));
                String type = itemLink.findElement(By.cssSelector(".info_area .type")).getText();

                if (type.contains("아파트") || type.contains("오피스텔")) {
                    WebElement titleElement = item.findElement(By.cssSelector(".item_inner .title"));
                    resultList.add(titleElement.getText());
                }
            }

            return resultList;
        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            driver.get(WEB_BASE_URL);
        }
    }

}
