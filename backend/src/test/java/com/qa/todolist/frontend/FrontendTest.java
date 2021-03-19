package com.qa.todolist.frontend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.todolist.data.model.Category;
import com.qa.todolist.data.model.Content;
import com.qa.todolist.data.model.Title;
import com.qa.todolist.data.model.Todo;
import com.qa.todolist.frontend.category.CreateCategory;
import com.qa.todolist.frontend.category.EditCategory;
import com.qa.todolist.frontend.todo.EditTodo;
import com.qa.todolist.frontend.home.ReadCategory;
import com.qa.todolist.frontend.home.ReadTodo;
import com.qa.todolist.frontend.settings.SettingsPage;
import com.qa.todolist.frontend.todo.CreateTodo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.opentest4j.AssertionFailedError;
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
        } catch (Exception | AssertionFailedError e) {
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
        } catch (Exception | AssertionFailedError e) {
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
        } catch (Exception | AssertionFailedError e) {
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
        } catch (Exception | AssertionFailedError e) {
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
        } catch (Exception | AssertionFailedError e) {
            test.fail("Create Category Discard Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/CreateCategoryDiscardButtonTest.png"));
        test.pass("Create Category Discard Button Test passed");
    }

    @Test
    void editCategoryUpdateTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Category Update Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            EditCategory.update("updated category", driver);
            driver.get(frontendURL + "index.html");
            assertThat(ReadCategory.findName(1, driver)).isEqualTo("updated category");
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Category Update Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/EditCategoryUpdateButtonTest.png"));
        test.pass("Edit Category Update Button Test passed");
    }

    @Test
    void editCategoryResetTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Category Reset Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(EditCategory.reset("updated category", driver)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Category Reset Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/EditCategoryResetButtonTest.png"));
        test.pass("Edit Category Reset Button Test passed");
    }

    @Test
    void editCategoryCancelTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Category Cancel Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(EditCategory.cancel(driver)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Category Cancel Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/EditCategoryCancelButtonTest.png"));
        test.pass("Edit Category Cancel Button Test passed");
    }

    @Test
    void editCategoryDeleteTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Category Delete Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            By category = By.id("category-1");
            assertThat(EditCategory.delete(driver)).isTrue();
            driver.get(frontendURL + "index.html");
            assertThrows(NoSuchElementException.class, () -> {
                driver.findElement(category);
            });
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Category Delete Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/EditCategoryDeleteButtonTest.png"));
        test.pass("Edit Category Delete Button Test passed");
    }

    @Test
    void readTodoIdTest() throws Exception {
        ExtentTest test = extentReport.createTest("Todo ID Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(ReadTodo.findId(1, driver)).isEqualTo("ID: 1");
        } catch (Exception | AssertionFailedError e) {
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
        } catch (Exception | AssertionFailedError e) {
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
        } catch (Exception | AssertionFailedError e) {
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
            assertThat(ReadTodo.findTitle(2, driver)).isEqualTo("new todo");
            assertThat(ReadTodo.findContent(2, driver)).isEqualTo("new todo contents");
            assertThat(ReadTodo.findId(2, driver)).isEqualTo("ID: 2");
        } catch (Exception | AssertionFailedError e) {
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
        } catch (Exception | AssertionFailedError e) {
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
        } catch (Exception | AssertionFailedError e) {
            test.fail("Create Todo Discard Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/CreateTodoDiscardButtonTest.png"));
        test.pass("Create Todo Discard Button Test passed");
    }

    @Test
    void editTodoUpdateTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Todo Update Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            EditTodo.update("updated todo", "updated todo content", driver);
            driver.get(frontendURL + "index.html");
            assertThat(ReadTodo.findTitle(1, driver)).isEqualTo("updated todo");
            assertThat(ReadTodo.findContent(1, driver)).isEqualTo("updated todo content");
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Todo Update Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/EditTodoUpdateButtonTest.png"));
        test.pass("Edit Todo Update Button Test passed");
    }

    @Test
    void editTodoResetTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Todo Reset Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(EditTodo.reset("updated todo", "updated todo content", driver)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Todo Reset Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/EditTodoResetButtonTest.png"));
        test.pass("Edit Todo Reset Button Test passed");
    }

    @Test
    void editTodoCancelTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Todo Cancel Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(EditTodo.cancel(driver)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Todo Cancel Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/EditTodoCancelButtonTest.png"));
        test.pass("Edit Todo Cancel Button Test passed");
    }

    @Test
    void editTodoDeleteTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Todo Delete Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            By todo = By.id("todo-1");
            assertThat(EditTodo.delete(driver)).isTrue();
            driver.get(frontendURL + "index.html");
            assertThrows(NoSuchElementException.class, () -> {
                driver.findElement(todo);
            });
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Todo Delete Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/EditTodoDeleteButtonTest.png"));
        test.pass("Edit Todo Delete Button Test passed");
    }

    @Test
    void backendURLSetTest() throws Exception {
        ExtentTest test = extentReport.createTest("Settings Backend URL Set Test");
        test.assignAuthor("Ali Hamza M");
        try {
            SettingsPage.setUrl("http://localhost:2342", driver);
            assertThat(driver.manage().getCookieNamed("serverurl").getValue()).isEqualTo("http://localhost:2342");
        } catch (Exception | AssertionFailedError e) {
            test.fail("Settings Backend URL Set Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/SettingsBackendURLSetTest.png"));
        test.pass("Settings Backend URL Set Test passed");
    }

    @Test
    void backendURLGetTest() throws Exception {
        ExtentTest test = extentReport.createTest("Settings Backend URL Get Test");
        test.assignAuthor("Ali Hamza M");
        try {
            String backendUrl = driver.manage().getCookieNamed("serverurl").getValue();
            assertThat(SettingsPage.getUrl(backendUrl, driver)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Settings Backend URL Get Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/SettingsBackendURLGetTest.png"));
        test.pass("Settings Backend URL Get Test passed");
    }

    @Test
    void createCategoryNavTest() throws Exception {
        ExtentTest test = extentReport.createTest("Create Category Navigation Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(Navigation.createCategory("createCategory.html", driver)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Create Category Navigation Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/CreateCategoryNavigationTest.png"));
        test.pass("Create Category Navigation Test passed");
    }

    @Test
    void createTodoNavTest() throws Exception {
        ExtentTest test = extentReport.createTest("Create Todo Navigation Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(Navigation.createTodo("createTodo.html", driver)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Create Todo Navigation Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/CreateTodoNavigationTest.png"));
        test.pass("Create Todo Navigation Test passed");

    }

    @Test
    void editCategoryNavTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Category Navigation Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(Navigation.editCategory("editCategory.html?id=1", driver)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Category Navigation Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/EditCategoryNavigationTest.png"));
        test.pass("Edit Category Navigation Test passed");
    }

    @Test
    void editTodoNavTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Todo Navigation Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(Navigation.editTodo("editTodo.html?id=1", driver)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Todo Navigation Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/EditTodoNavigationTest.png"));
        test.pass("Edit Todo Navigation Test passed");
    }

    @Test
    void settingsNavTest() throws Exception {
        ExtentTest test = extentReport.createTest("Settings Navigation Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(Navigation.settings("settings.html", driver)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Settings Navigation Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/SettingsNavigationTest.png"));
        test.pass("Settings Navigation Test passed");
    }

    @Test
    void homeNavTest() throws Exception {
        ExtentTest test = extentReport.createTest("Home Navigation Test");
        test.assignAuthor("Ali Hamza M");
        try {
            driver.get(frontendURL + "settings.html");
            assertThat(Navigation.home("index.html", driver)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Home Navigation Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./target/reports/HomeNavigationTest.png"));
        test.pass("Home Navigation Test passed");
    }

    @AfterAll
    public static void teardown() {
        driver.quit();
        extentReport.flush();
    }
}
