package com.qa.todolist.frontend;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import com.qa.todolist.data.model.Category;
import com.qa.todolist.data.model.Content;
import com.qa.todolist.data.model.Title;
import com.qa.todolist.data.model.Todo;
import com.qa.todolist.frontend.category.CreateCategory;
import com.qa.todolist.frontend.home.ReadCategory;
import com.qa.todolist.frontend.home.ReadTodo;
import com.qa.todolist.frontend.todo.CreateTodo;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.junit.jupiter.api.AfterAll;
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
        } catch (Exception e) {
            test.fail("Category ID Test failed\nError: " + e);
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
        } catch (Exception e) {
            test.fail("Category Name Test failed\nError: " + e);
            throw e;
        }

        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/CategoryNameTest.png"));
        test.pass("Category Name Test passed");
    }

    @Test
    void createCategoryCreateTest() throws Exception {
        ExtentTest test = extentReport.createTest("Create Category Create Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            CreateCategory.create("new category", driver);
            driver.get(frontendURL + "index.html");
            assertThat(ReadCategory.findName(2, driver)).isEqualTo("new category");
        } catch (Exception e) {
            test.fail("Create Category Create Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/CreateCategoryCreateButtonTest.png"));
        test.pass("Create Category Create Button Test passed");
    }

    @Test
    void createCategoryResetTest() throws Exception {
        ExtentTest test = extentReport.createTest("Create Category Reset Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(CreateCategory.reset("new category", driver)).isTrue();
        } catch (Exception e) {
            test.fail("Create Category Reset Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/CreateCategoryResetButtonTest.png"));
        test.pass("Create Category Reset Button Test passed");
    }

    @Test
    void createCategoryDiscardTest() throws Exception {
        ExtentTest test = extentReport.createTest("Create Category Discard Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(CreateCategory.discard("new category", driver)).isTrue();
        } catch (Exception e) {
            test.fail("Create Category Discard Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/CreateCategoryDiscardButtonTest.png"));
        test.pass("Create Category Discard Button Test passed");
    }

    // @Test
    // void editCategoryTest() {
    // }

    // @Test
    // void deleteCategoryTest() {
    // }

    @Test
    void readTodoIdTest() throws Exception {
        ExtentTest test = extentReport.createTest("Todo ID Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(ReadTodo.findId(1, driver)).isEqualTo("ID: 1");
        } catch (Exception e) {
            test.fail("Todo ID Test failed\nError: " + e);
            throw e;
        }

        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/TodoIdTest.png"));
        test.pass("Todo ID Test passed");
    }

    @Test
    void readTodoTitleTest() throws Exception {
        ExtentTest test = extentReport.createTest("Todo Title Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(ReadTodo.findTitle(1, driver)).isEqualTo("test");
        } catch (Exception e) {
            test.fail("Todo Title Test failed\nError: " + e);
            throw e;
        }

        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/TodoTitleTest.png"));
        test.pass("Todo Title Test passed");
    }

    @Test
    void readTodoContentTest() throws Exception {
        ExtentTest test = extentReport.createTest("Todo Content Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(ReadTodo.findContent(1, driver)).isEqualTo("test");
        } catch (Exception e) {
            test.fail("Todo Content Test failed\nError: " + e);
            throw e;
        }

        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/TodoContentTest.png"));
        test.pass("Todo Content Test passed");
    }

    @Test
    void createTodoCreateTest() throws Exception {
        ExtentTest test = extentReport.createTest("Create Todo Create Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            CreateTodo.create("new todo", "new todo contents", 1, driver);
            driver.get(frontendURL + "index.html");
            assertThat(ReadTodo.findTitle(1, driver)).isEqualTo("new todo");
            assertThat(ReadTodo.findContent(1, driver)).isEqualTo("new todo contents");
            assertThat(ReadTodo.findId(1, driver)).isEqualTo("1");
        } catch (Exception e) {
            test.fail("Create Todo Create Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/CreateTodoCreateButtonTest.png"));
        test.pass("Create Todo Create Button Test passed");
    }

    @Test
    void createTodoResetTest() throws Exception {
        ExtentTest test = extentReport.createTest("Create Todo Reset Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(CreateTodo.reset("new todo", "new todo contents", driver)).isTrue();
        } catch (Exception e) {
            test.fail("Create Todo Reset Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/CreateTodoResetButtonTest.png"));
        test.pass("Create Todo Reset Button Test passed");
    }

    @Test
    void createTodoDiscardTest() throws Exception {
        ExtentTest test = extentReport.createTest("Create Todo Discard Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(CreateTodo.discard("new todo", "new todo contents", driver)).isTrue();
        } catch (Exception e) {
            test.fail("Create Todo Discard Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/CreateTodoDiscardButtonTest.png"));
        test.pass("Create Todo Discard Button Test passed");
    }

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
