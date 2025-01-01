package com.example.handy_home.data.scheduler;

import com.example.handy_home.data.repositories.NaverRealtyRepository;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v131.network.Network;
import org.openqa.selenium.devtools.v131.network.model.Headers;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@Component
public class UpdateNaverRealtyTokenScheduler {

    // 드라이버 관련 상수
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH_WINDOWS = "static/chromedriver.exe";
    private static final String WEB_DRIVER_PATH_MAC_LINUX = "static/chromedriver";

    private static final String WEB_BASE_URL = "https://new.land.naver.com/complexes";

    private WebDriver driver;
    private DevTools devTools;

    private void initWebDriver() {
        String os = System.getProperty("os.name").toLowerCase();
        String driverPath = os.contains("win")
                ? WEB_DRIVER_PATH_WINDOWS
                : WEB_DRIVER_PATH_MAC_LINUX;

        String resolvedPath = Objects.requireNonNull(getClass().getClassLoader().getResource(driverPath)).getPath();

        if (os.contains("win") && resolvedPath.startsWith("/")) {
            resolvedPath = resolvedPath.substring(1);
        }

        System.setProperty(WEB_DRIVER_ID, resolvedPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("headless");

        driver = new ChromeDriver(options);
        devTools = ((ChromeDriver) driver).getDevTools();
    }

    private void resetWebDriver() {
        try {
            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.get("about:blank");
                driver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver = null;
            devTools = null;
        }
    }

    @Scheduled(fixedDelay = 9000000)
    private void updateToken() throws InterruptedException {
        System.out.println("Update Token...");
        initWebDriver();

        CountDownLatch latch = new CountDownLatch(1);

        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(), request -> {
            final Headers headers = request.getRequest().getHeaders();

            if (headers.containsKey("authorization")) {
                NaverRealtyRepository.setNaverRealtyToken((String) headers.get("authorization"));
                latch.countDown();
            }
        });

        driver.get(WEB_BASE_URL);

        latch.await();

        resetWebDriver();
        System.out.println("Update Token is Completed!");
    }
}
