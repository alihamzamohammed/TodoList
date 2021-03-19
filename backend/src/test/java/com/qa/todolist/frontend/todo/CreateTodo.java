package com.qa.todolist.frontend.todo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateTodo {

    private static String frontendURL = "http://localhost:5500/frontend/";

    public static Boolean create(String title, String content, int category, WebDriver driver) {
        driver.get(frontendURL + "createTodo.html");
        WebElement titleInput = driver.findElement(By.id("title-input"));
        titleInput.sendKeys(title);
        WebElement contentInput = driver.findElement(By.id("content-input"));
        contentInput.sendKeys(content);
        Select categorySelection = new Select(driver.findElement(By.id("category-selection")));
        categorySelection.selectByValue(String.valueOf(category));
        WebElement createButton = driver.findElement(By.id("create-button"));
        createButton.click();
        By response = By.id("response");
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(response));
        return driver.findElement(response).getCssValue("color").equals("green");
    }

    public static Boolean reset(String title, String content, WebDriver driver) {
        driver.get(frontendURL + "createTodo.html");
        WebElement titleInput = driver.findElement(By.id("title-input"));
        titleInput.sendKeys(title);
        WebElement contentInput = driver.findElement(By.id("content-input"));
        contentInput.sendKeys(content);
        WebElement resetButton = driver.findElement(By.id("reset-button"));
        resetButton.click();
        return titleInput.getAttribute("value").equals("") && contentInput.getAttribute("value").equals("");
    }

    public static Boolean discard(String title, String content, WebDriver driver) {
        driver.get(frontendURL + "createTodo.html");
        WebElement titleInput = driver.findElement(By.id("title-input"));
        titleInput.sendKeys(title);
        WebElement contentInput = driver.findElement(By.id("content-input"));
        contentInput.sendKeys(content);
        WebElement discardButton = driver.findElement(By.id("discard-button"));
        discardButton.click();
        return driver.getCurrentUrl().endsWith("index.html");
    }
}
