package com.qa.todolist.frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Navigation {

    private static String frontendURL = "http://localhost:5500/frontend/";

    public static Boolean home(String page, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("/html/body/nav/div/div/ul/li[1]/a"))).click().build()
                .perform();
        return driver.getCurrentUrl().endsWith(page);
    }

    public static Boolean createCategory(String page, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("/html/body/nav/div/div/ul/li[2]/a"))).click()
                .moveToElement(driver.findElement(By.xpath("/html/body/nav/div/div/ul/li[2]/ul/li[2]/a"))).click()
                .build().perform();
        return driver.getCurrentUrl().endsWith(page);
    }

    public static Boolean createTodo(String page, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("/html/body/nav/div/div/ul/li[2]/a"))).click()
                .moveToElement(driver.findElement(By.xpath("/html/body/nav/div/div/ul/li[2]/ul/li[1]/a"))).click()
                .build().perform();
        return driver.getCurrentUrl().endsWith(page);
    }

    public static Boolean editCategory(String page, WebDriver driver) {
        Actions actions = new Actions(driver);
        By category = By.cssSelector("#category-1 > div.row.category-header > div.col-11.category-name > h3");
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(category));
        actions.moveToElement(driver.findElement(category)).click().build().perform();
        return driver.getCurrentUrl().endsWith(page);
    }

    public static Boolean editTodo(String page, WebDriver driver) {
        // CreateTodo.create("title", "content", 1, driver);
        driver.get(frontendURL + "index.html");
        Actions actions = new Actions(driver);
        By todo = By.cssSelector("#todo-1 > a");
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(todo));
        actions.moveToElement(driver.findElement(todo)).click().build().perform();
        return driver.getCurrentUrl().endsWith(page);
    }

    public static Boolean settings(String page, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("/html/body/nav/div/div/ul/li[3]/a"))).click().build()
                .perform();
        return driver.getCurrentUrl().endsWith(page);
    }

}
