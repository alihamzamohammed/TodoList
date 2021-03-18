package com.qa.todolist.frontend;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import com.qa.todolist.data.model.Category;
import com.qa.todolist.data.model.Content;
import com.qa.todolist.data.model.Title;
import com.qa.todolist.data.model.Todo;
import com.qa.todolist.frontend.home.ReadCategory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
@Sql(scripts = { "classpath:test-schema.sql",
        "classpath:test-data-todo.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class FrontendTest {

    @LocalServerPort
    private static int port;
    private static String frontendURL = "http://localhost:5500/frontend/";
    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;
    // @Autowired
    // private CategoryRepository categoryRepository;
    // @Autowired
    // private TodoRepository todoRepository;
    // @Autowired
    // private TitleRepository titleRepository;
    // @Autowired
    // private ContentRepository contentRepository;
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

        extent = new ExtentReports("src/test/resources/reports/report.html", true);
        test = extent.startTest("ExtentDemo");
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
        driver.manage().addCookie(new Cookie("serverurl", "http://localhost:" + port));

    }

    @BeforeEach
    public void foundation() throws TimeoutException {
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test
    void readCategoryTest() {
        driver.get(frontendURL + "index.html");
        assertThat(ReadCategory.findId(1, driver)).isEqualTo(String.valueOf(1));
        assertThat(ReadCategory.findName(1, driver)).isEqualTo("test");
    }

    @Test
    void createCategoryTest() {
    }

    @Test
    void editCategoryTest() {
    }

    @Test
    void deleteCategoryTest() {
    }

    @Test
    void readTodoTest() {
    }

    @Test
    void createTodoTest() {
    }

    @Test
    void editTodoTest() {
    }

    @Test
    void deleteTodoTest() {
    }

    @Test
    void backendURLSetTest() {
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
