package com.qa.todolist.frontend.todo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditTodo {

    public static Boolean update(String title, String content, WebDriver driver, String frontendURL) {
        driver.get(frontendURL + "editTodo.html?id=" + 1);
        WebElement titleInput = driver.findElement(By.id("title-input"));
        titleInput.clear();
        titleInput.sendKeys(title);
        WebElement contentInput = driver.findElement(By.id("content-input"));
        contentInput.clear();
        contentInput.sendKeys(content);
        WebElement createButton = driver.findElement(By.id("update-button"));
        createButton.click();
        By response = By.id("response");
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(response));
        return driver.findElement(response).getText().startsWith("To-Do Item successfully updated!");
    }

    public static Boolean reset(String title, String content, WebDriver driver, String frontendURL) {
        driver.get(frontendURL + "editTodo.html?id=" + 1);
        By titleSelector = By.id("title-input");
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(titleSelector));
        WebElement titleInput = driver.findElement(titleSelector);
        titleInput.sendKeys(title);
        WebElement contentInput = driver.findElement(By.id("content-input"));
        contentInput.sendKeys(content);
        WebElement resetButton = driver.findElement(By.id("reset-button"));
        resetButton.click();
        return titleInput.getAttribute("value").equals("");
    }

    public static Boolean cancel(WebDriver driver, String frontendURL) {
        driver.get(frontendURL + "editTodo.html?id=" + 1);
        WebElement cancelButton = driver.findElement(By.id("cancel-button"));
        cancelButton.click();
        return driver.getCurrentUrl().endsWith("index.html");
    }

    public static Boolean delete(WebDriver driver, String frontendURL) {
        driver.get(frontendURL + "editTodo.html?id=" + 1);
        WebElement deleteButton = driver.findElement(By.id("delete-button"));
        deleteButton.click();
        By response = By.id("response");
        new WebDriverWait(driver, 7).until(ExpectedConditions.visibilityOfElementLocated(response));
        return driver.findElement(response).getText().equals("To-Do Item successfully deleted!");
    }
}
