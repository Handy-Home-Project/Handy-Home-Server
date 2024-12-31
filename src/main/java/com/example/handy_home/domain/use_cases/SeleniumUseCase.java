package com.example.handy_home.domain.use_cases;

import org.apache.commons.logging.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
public class SeleniumUseCase {

    // 드라이버 관련 상수
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH_WINDOWS = "static/chromedriver.exe";
    private static final String WEB_DRIVER_PATH_MAC_LINUX = "static/chromedriver";

    // 웹 관련 상수
    private static final String WEB_BASE_URL = "https://new.land.naver.com/complexes?ms=37.3595704,127.105399,16&a=APT:ABYG:JGC:PRE&e=RETAIL";

    // WebDriver (전역 상태 변수)
    private WebDriver driver;

    public SeleniumUseCase() {
        initWebDriver();
    }

    private void initWebDriver() {
        try {
            // 운영체제에 따라 드라이버 경로 설정
            String os = System.getProperty("os.name").toLowerCase();
            String driverPath = os.contains("win")
                    ? WEB_DRIVER_PATH_WINDOWS
                    : WEB_DRIVER_PATH_MAC_LINUX;

            String resolvedPath = Objects.requireNonNull(getClass().getClassLoader().getResource(driverPath)).getPath();

            // Windows에서 경로 수정
            if (os.contains("win") && resolvedPath.startsWith("/")) {
                resolvedPath = resolvedPath.substring(1);
            }

            System.setProperty(WEB_DRIVER_ID, resolvedPath);

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("headless");

            driver = new ChromeDriver(options);
            driver.get(WEB_BASE_URL);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }

    public List<Map<String, String>> searchKeywordInNaverRealty(String keyword) {
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

            final List<Map<String, String>> resultList = new ArrayList<>();

            for (WebElement item : itemList) {
                WebElement itemLink = item.findElement(By.cssSelector(".item_inner .item_link"));
                String type = itemLink.findElement(By.cssSelector(".info_area .type")).getText();

                if (type.contains("아파트") || type.contains("오피스텔")) {
                    WebElement titleElement = item.findElement(By.cssSelector(".item_inner .title"));
                    WebElement addressElement = item.findElement(By.cssSelector(".item_inner .address"));

                    String title = titleElement.getText();
                    String address = addressElement.getText();

                    Map<String, String> data = new HashMap<>();
                    data.put("title", title);
                    data.put("address", address);

                    resultList.add(data);
                }
            }

            return resultList;

        } catch (Exception e) {
            e.printStackTrace(); // 디버깅용 예외 출력
            return new ArrayList<>();
        } finally {
            driver.get(WEB_BASE_URL);
        }
    }
}
