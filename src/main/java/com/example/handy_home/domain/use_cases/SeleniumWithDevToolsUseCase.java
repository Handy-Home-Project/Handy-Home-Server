//package com.example.handy_home.domain.use_cases;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.devtools.DevTools;
//import org.openqa.selenium.devtools.v131.network.Network;
//import org.openqa.selenium.devtools.v131.network.model.Response;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.util.*;
//
//@Service
//public class SeleniumWithDevToolsUseCase {
//
//    // 드라이버 관련 상수
//    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
//    private static final String WEB_DRIVER_PATH_WINDOWS = "static/chromedriver.exe";
//    private static final String WEB_DRIVER_PATH_MAC_LINUX = "static/chromedriver";
//
//    // WebDriver 및 DevTools
//    private WebDriver driver;
//    private DevTools devTools;
//
//    public SeleniumWithDevToolsUseCase() {
//        initWebDriver();
//    }
//
//    private void initWebDriver() {
//        try {
//            String os = System.getProperty("os.name").toLowerCase();
//            String driverPath = os.contains("win")
//                    ? WEB_DRIVER_PATH_WINDOWS
//                    : WEB_DRIVER_PATH_MAC_LINUX;
//
//            String resolvedPath = Objects.requireNonNull(getClass().getClassLoader().getResource(driverPath)).getPath();
//
//            // Windows 경로 수정
//            if (os.contains("win") && resolvedPath.startsWith("/")) {
//                resolvedPath = resolvedPath.substring(1);
//            }
//
//            System.setProperty(WEB_DRIVER_ID, resolvedPath);
//
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--remote-allow-origins=*");
//            // options.addArguments("headless"); // 필요 시 헤드리스 모드 사용
//
//            driver = new ChromeDriver(options);
//            devTools = ((ChromeDriver) driver).getDevTools();
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
//        }
//    }
//
//    private void resetSession() {
//        try {
//            if (driver != null) {
//                driver.manage().deleteAllCookies();
//                driver.get("about:blank");
//                driver.quit();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            driver = null;
//            devTools = null;
//        }
//        initWebDriver();
//    }
//
//    public List<Map<String, Object>> fetchFloorPlan(String deepLink) {
//        List<Map<String, Object>> floorPlanDetails = new ArrayList<>();
//
//        if (driver == null || devTools == null) {
//            initWebDriver();
//        }
//
//        try {
//            resetSession(); // 각 호출마다 세션 초기화
//
//            // DevTools 시작 및 세션 생성
//            devTools.createSession();
//            System.out.println("DevTools session created successfully.");
//
//            // 네트워크 트래픽 활성화
//            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
//
//            // API 응답 캡처
//            devTools.addListener(Network.responseReceived(), response -> {
//                Response networkResponse = response.getResponse();
//                String url = networkResponse.getUrl();
//
//                // URL 및 상태 코드 출력
//                System.out.println("Captured URL: " + url);
//                System.out.println("Request ID: " + response.getRequestId());
//                System.out.println("Status Code: " + networkResponse.getStatus());
//
//                // 성공적인 상태 코드인지 확인
//                if (networkResponse.getStatus() == 200 && url.contains("/api/complexes/") && url.contains("initial=Y")) {
//                    try {
//                        // 대기 시간 추가
//                        Thread.sleep(1000); // 요청과 응답 사이의 처리 시간 대기
//
//                        // 요청 ID로 응답 본문 가져오기
//                        String responseBody = devTools.send(Network.getResponseBody(response.getRequestId())).getBody();
//
//                        // 응답 본문 출력 (디버깅용)
//                        System.out.println("Response Body: " + responseBody);
//
//                        // JSON 데이터를 파싱하여 필요한 정보 추출
//                        Map<String, Object> apiResponse = new com.fasterxml.jackson.databind.ObjectMapper().readValue(responseBody, Map.class);
//                        List<Map<String, Object>> areaList = (List<Map<String, Object>>) apiResponse.get("areaList");
//
//                        if (areaList != null) {
//                            floorPlanDetails.addAll(areaList);
//                        } else {
//                            System.err.println("No 'areaList' found in the response.");
//                        }
//
//                    } catch (InterruptedException e) {
//                        System.err.println("Interrupted while waiting: " + e.getMessage());
//                        Thread.currentThread().interrupt(); // 스레드 복구
//                    } catch (org.openqa.selenium.devtools.DevToolsException e) {
//                        // 요청 ID에 대한 응답 데이터 없음
//                        System.err.println("Failed to fetch response body for Request ID: " + response.getRequestId());
//                        System.err.println("Error Message: " + e.getMessage());
//                    } catch (Exception e) {
//                        // 일반적인 예외 처리
//                        e.printStackTrace();
//                    }
//                } else {
//                    System.err.println("Unsuccessful request or irrelevant URL.");
//                }
//            });
//
//            // 페이지 이동
//            driver.get("https://new.land.naver.com" + deepLink);
//
//            // 버튼 클릭
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.cssSelector("#summaryInfo > div.complex_summary_info > div.complex_detail_link > button:nth-child(1)")
//            ));
//            button.click();
//
//            // 추가 대기 시간
//            Thread.sleep(5000);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            resetSession(); // 요청 완료 후 세션 정리
//        }
//
//        return floorPlanDetails;
//    }
//}
