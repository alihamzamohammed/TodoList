package com.qa.todolist.frontend;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import com.qa.todolist.data.model.Category;
import com.qa.todolist.data.model.Content;
import com.qa.todolist.data.model.Title;
import com.qa.todolist.data.model.Todo;
import com.qa.todolist.frontend.home.ReadCategory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
@Sql(scripts = { "classpath:test-schema.sql",
        "classpath:test-data-todo.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = "spring.datasource.url=jdbc:h2:mem:testDB;DB_CLOSE_ON_EXIT=FALSE")
class FrontendTest {

    @LocalServerPort
    private int port;
    private static String frontendURL = "http://localhost:5500/frontend/";
    private static WebDriver driver;
    private static ExtentReports extentReport;
    private static ExtentSparkReporter sparkReporter;
    private static ExtentTest test;
    private static Category category;
    private static Title title;
    private static Content content;
    private static Todo todo;

    @BeforeAll
    public static void init() {
        category = new Category("Test Category");
        title = new Title("Test Title");
        content = new Content("Test Content");
        todo = new Todo(title, content, category);

        extentReport = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("./target/reports/Report.html");
        extentReport.attachReporter(sparkReporter);

        System.setProperty("webdriver.chrome.driver", "src//test//resources//driver//chromedriver.exe");
        ChromeOptions cOptions = new ChromeOptions();
        cOptions.setHeadless(false);
        cOptions.addArguments("--no-sandbox");

        cOptions.setCapability("profile.default_content_setting_values.cookies", 2);
        cOptions.setCapability("network.cookie.cookieBehavior", 2);
        cOptions.setCapability("profile.block_third_party_cookies", true);

        driver = new ChromeDriver(cOptions);
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.get(frontendURL + "index.html");
    }

    @BeforeEach
    public void foundation() throws TimeoutException {
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(frontendURL + "index.html");
        driver.manage().addCookie(new Cookie("serverurl", "http://localhost:" + port));
    }

    @Test
    void readCategoryIdTest() throws Exception {

        ExtentTest test = extentReport.createTest("Category ID Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(ReadCategory.findId(1, driver)).isEqualTo(String.valueOf(1));
        } catch (AssertionError e) {
            test.fail("Category ID Test failed");
            throw e;
        }

        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/CategoryIdTest.png"));
        test.pass("Category ID Test passed");
    }

    @Test
    void readCategoryNameTest() throws Exception {
        ExtentTest test = extentReport.createTest("Category Name Test");
        test.assignAuthor("Ali Hamza M");
        try {

            assertThat(ReadCategory.findName(1, driver)).isEqualTo("test");
        } catch (AssertionError e) {
            test.fail("Category Name Test failed");
            throw e;
        }

        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/CategoryNameTest.png"));
        test.pass("Category Name Test passed");
    }

    // @Test
    // void createCategoryTest() {
    // }

    // @Test
    // void editCategoryTest() {
    // }

    // @Test
    // void deleteCategoryTest() {
    // }

    // @Test
    // void readTodoTest() {
    // }

    // @Test
    // void createTodoTest() {
    // }

    // @Test
    // void editTodoTest() {
    // }

    // @Test
    // void deleteTodoTest() {
    // }

    // @Test
    // void backendURLSetTest() {
    // }

    // @Test
    // void createCategoryNavTest() {}

    // @Test
    // void createTodoNavTest() {}

    // @Test
    // void editCategoryNavTest() {}

    // @Test
    // void editTodoNavTest() {}

    // @Test
    // void settingsNavTest() {}

    // @AfterEach
    // public void closeDriver() {
    // driver.close();
    // }

    @AfterAll
    public static void teardown() {
        driver.quit();
        extentReport.flush();
    }
}
