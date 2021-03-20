package com.qa.todolist.frontend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.todolist.frontend.category.CreateCategory;
import com.qa.todolist.frontend.category.EditCategory;
import com.qa.todolist.frontend.home.ReadCategory;
import com.qa.todolist.frontend.home.ReadTodo;
import com.qa.todolist.frontend.settings.SettingsPage;
import com.qa.todolist.frontend.todo.CreateTodo;
import com.qa.todolist.frontend.todo.EditTodo;

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

    private static String frontendURL;
    private static WebDriver driver;
    private static ExtentReports extentReport;
    private static ExtentSparkReporter sparkReporter;

    @BeforeAll
    public static void init() {
        extentReport = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("./reports/frontend/Report.html");
        extentReport.attachReporter(sparkReporter);

        System.setProperty("webdriver.chrome.driver", "src//test//resources//driver//chromedriver.exe");
        ChromeOptions cOptions = new ChromeOptions();
        cOptions.setHeadless(false);
        cOptions.addArguments("--no-sandbox");

        cOptions.setCapability("profile.default_content_setting_values.cookies", 2);
        cOptions.setCapability("network.cookie.cookieBehavior", 2);
        cOptions.setCapability("profile.block_third_party_cookies", true);

        driver = new ChromeDriver(cOptions);
        driver.manage().window().setSize(new Dimension(1366, 768));
        // driver.get(frontendURL + "index.html");
    }

    @BeforeEach
    public void foundation() throws TimeoutException, IOException {
        Properties prop = new Properties();
        prop.load(this.getClass().getClassLoader().getResourceAsStream("application-test.properties"));
        frontendURL = prop.getProperty("testing.frontend.url");
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

        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./reports/frontend/screenshots/CategoryIdTest.png"));
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

        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./reports/frontend/screenshots/CategoryNameTest.png"));
        test.pass("Category Name Test passed");
    }

    @Test
    void createCategoryCreateTest() throws Exception {
        ExtentTest test = extentReport.createTest("Create Category Create Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            CreateCategory.create("new category", driver, frontendURL);
            driver.get(frontendURL + "index.html");
            assertThat(ReadCategory.findName(2, driver)).isEqualTo("new category");
        } catch (Exception | AssertionFailedError e) {
            test.fail("Create Category Create Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/CreateCategoryCreateButtonTest.png"));
        test.pass("Create Category Create Button Test passed");
    }

    @Test
    void createCategoryResetTest() throws Exception {
        ExtentTest test = extentReport.createTest("Create Category Reset Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(CreateCategory.reset("new category", driver, frontendURL)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Create Category Reset Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/CreateCategoryResetButtonTest.png"));
        test.pass("Create Category Reset Button Test passed");
    }

    @Test
    void createCategoryDiscardTest() throws Exception {
        ExtentTest test = extentReport.createTest("Create Category Discard Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(CreateCategory.discard("new category", driver, frontendURL)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Create Category Discard Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/CreateCategoryDiscardButtonTest.png"));
        test.pass("Create Category Discard Button Test passed");
    }

    @Test
    void editCategoryUpdateTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Category Update Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            EditCategory.update("updated category", driver, frontendURL);
            driver.get(frontendURL + "index.html");
            assertThat(ReadCategory.findName(1, driver)).isEqualTo("updated category");
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Category Update Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/EditCategoryUpdateButtonTest.png"));
        test.pass("Edit Category Update Button Test passed");
    }

    @Test
    void editCategoryResetTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Category Reset Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(EditCategory.reset("updated category", driver, frontendURL)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Category Reset Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/EditCategoryResetButtonTest.png"));
        test.pass("Edit Category Reset Button Test passed");
    }

    @Test
    void editCategoryCancelTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Category Cancel Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(EditCategory.cancel(driver, frontendURL)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Category Cancel Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/EditCategoryCancelButtonTest.png"));
        test.pass("Edit Category Cancel Button Test passed");
    }

    @Test
    void editCategoryDeleteTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Category Delete Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            By category = By.id("category-1");
            assertThat(EditCategory.delete(driver, frontendURL)).isTrue();
            driver.get(frontendURL + "index.html");
            assertThrows(NoSuchElementException.class, () -> {
                driver.findElement(category);
            });
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Category Delete Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/EditCategoryDeleteButtonTest.png"));
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

        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./reports/frontend/screenshots/TodoIdTest.png"));
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

        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./reports/frontend/screenshots/TodoTitleTest.png"));
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

        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./reports/frontend/screenshots/TodoContentTest.png"));
        test.pass("Todo Content Test passed");
    }

    @Test
    void createTodoCreateTest() throws Exception {
        ExtentTest test = extentReport.createTest("Create Todo Create Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            CreateTodo.create("new todo", "new todo contents", 1, driver, frontendURL);
            driver.get(frontendURL + "index.html");
            assertThat(ReadTodo.findTitle(2, driver)).isEqualTo("new todo");
            assertThat(ReadTodo.findContent(2, driver)).isEqualTo("new todo contents");
            assertThat(ReadTodo.findId(2, driver)).isEqualTo("ID: 2");
        } catch (Exception | AssertionFailedError e) {
            test.fail("Create Todo Create Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/CreateTodoCreateButtonTest.png"));
        test.pass("Create Todo Create Button Test passed");
    }

    @Test
    void createTodoResetTest() throws Exception {
        ExtentTest test = extentReport.createTest("Create Todo Reset Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(CreateTodo.reset("new todo", "new todo contents", driver, frontendURL)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Create Todo Reset Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/CreateTodoResetButtonTest.png"));
        test.pass("Create Todo Reset Button Test passed");
    }

    @Test
    void createTodoDiscardTest() throws Exception {
        ExtentTest test = extentReport.createTest("Create Todo Discard Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(CreateTodo.discard("new todo", "new todo contents", driver, frontendURL)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Create Todo Discard Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/CreateTodoDiscardButtonTest.png"));
        test.pass("Create Todo Discard Button Test passed");
    }

    @Test
    void editTodoUpdateTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Todo Update Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            EditTodo.update("updated todo", "updated todo content", driver, frontendURL);
            driver.get(frontendURL + "index.html");
            assertThat(ReadTodo.findTitle(1, driver)).isEqualTo("updated todo");
            assertThat(ReadTodo.findContent(1, driver)).isEqualTo("updated todo content");
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Todo Update Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/EditTodoUpdateButtonTest.png"));
        test.pass("Edit Todo Update Button Test passed");
    }

    @Test
    void editTodoResetTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Todo Reset Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(EditTodo.reset("updated todo", "updated todo content", driver, frontendURL)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Todo Reset Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/EditTodoResetButtonTest.png"));
        test.pass("Edit Todo Reset Button Test passed");
    }

    @Test
    void editTodoCancelTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Todo Cancel Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(EditTodo.cancel(driver, frontendURL)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Todo Cancel Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/EditTodoCancelButtonTest.png"));
        test.pass("Edit Todo Cancel Button Test passed");
    }

    @Test
    void editTodoDeleteTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Todo Delete Button Test");
        test.assignAuthor("Ali Hamza M");
        try {
            By todo = By.id("todo-1");
            assertThat(EditTodo.delete(driver, frontendURL)).isTrue();
            driver.get(frontendURL + "index.html");
            assertThrows(NoSuchElementException.class, () -> {
                driver.findElement(todo);
            });
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Todo Delete Button Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/EditTodoDeleteButtonTest.png"));
        test.pass("Edit Todo Delete Button Test passed");
    }

    @Test
    void backendURLSetTest() throws Exception {
        ExtentTest test = extentReport.createTest("Settings Backend URL Set Test");
        test.assignAuthor("Ali Hamza M");
        try {
            SettingsPage.setUrl("http://localhost:2342", driver, frontendURL);
            assertThat(driver.manage().getCookieNamed("serverurl").getValue()).isEqualTo("http://localhost:2342");
        } catch (Exception | AssertionFailedError e) {
            test.fail("Settings Backend URL Set Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/SettingsBackendURLSetTest.png"));
        test.pass("Settings Backend URL Set Test passed");
    }

    @Test
    void backendURLGetTest() throws Exception {
        ExtentTest test = extentReport.createTest("Settings Backend URL Get Test");
        test.assignAuthor("Ali Hamza M");
        try {
            String backendUrl = driver.manage().getCookieNamed("serverurl").getValue();
            assertThat(SettingsPage.getUrl(backendUrl, driver, frontendURL)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Settings Backend URL Get Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/SettingsBackendURLGetTest.png"));
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
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/CreateCategoryNavigationTest.png"));
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
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/CreateTodoNavigationTest.png"));
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
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/EditCategoryNavigationTest.png"));
        test.pass("Edit Category Navigation Test passed");
    }

    @Test
    void editTodoNavTest() throws Exception {
        ExtentTest test = extentReport.createTest("Edit Todo Navigation Test");
        test.assignAuthor("Ali Hamza M");
        try {
            assertThat(Navigation.editTodo("editTodo.html?id=1", driver, frontendURL)).isTrue();
        } catch (Exception | AssertionFailedError e) {
            test.fail("Edit Todo Navigation Test failed\nError: " + e);
            throw e;
        }
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/EditTodoNavigationTest.png"));
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
        test.addScreenCaptureFromPath(
                Helper.snapShot(driver, "./reports/frontend/screenshots/SettingsNavigationTest.png"));
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
        test.addScreenCaptureFromPath(Helper.snapShot(driver, "./reports/frontend/screenshots/HomeNavigationTest.png"));
        test.pass("Home Navigation Test passed");
    }

    @AfterAll
    public static void teardown() {
        driver.quit();
        extentReport.flush();
    }
}
