package com.qa.todolist.frontend;

import java.util.concurrent.TimeUnit;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
class FrontendTest {

    private static String frontendURL = "/frontend/";
    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeAll
    public static void init() {
        extent = new ExtentReports("src/test/resources/reports/report.html", true);
        test = extent.startTest("ExtentDemo");
        System.setProperty("webdriver.chrome.driver", "src//test//resources//driver//chromedriver//chromedriver.exe");
        ChromeOptions cOptions = new ChromeOptions();
        cOptions.setHeadless(false);
        cOptions.addArguments("--no-sandbox");

        cOptions.setCapability("profile.default_content_setting_values.cookies", 2);
        cOptions.setCapability("network.cookie.cookieBehavior", 2);
        cOptions.setCapability("profile.block_third_party_cookies", true);

        driver = new ChromeDriver(cOptions);
        driver.manage().window().setSize(new Dimension(1280, 720));
    }

    @BeforeEach
    public void foundation() throws TimeoutException {
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test
    public void readCategoryTest() {
    }

    @Test
    public void createCategoryTest() {
    }

    @Test
    public void editCategoryTest() {
    }

    @Test
    public void deleteCategoryTest() {
    }

    @Test
    public void readTodoTest() {
    }

    @Test
    public void createTodoTest() {
    }

    @Test
    public void editTodoTest() {
    }

    @Test
    public void deleteTodoTest() {
    }

    @Test
    public void backendURLSetTest() {
    }

    @AfterEach
    public void closeDriver() {
        driver.close();
    }

    @AfterAll
    public static void teardown() {
        driver.quit();
        extent.endTest(test);
        extent.flush();
        extent.close();
    }
}
