package com.qa.todolist.frontend.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReadTodo {

    public static WebElement findTodo(int id, WebDriver driver) {
        By element = By.cssSelector("#todo-" + id);
        driver.navigate().refresh();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element);
    }

    public static String findId(int id, WebDriver driver) {
        return findTodo(id, driver).findElement(By.cssSelector(".todo-id")).getText();
    }

    public static String findTitle(int id, WebDriver driver) {
        return findTodo(id, driver).findElement(By.cssSelector(".card-title")).getText();
    }

    public static String findContent(int id, WebDriver driver) {
        return findTodo(id, driver).findElement(By.cssSelector(".card-text")).getText();
    }

}
