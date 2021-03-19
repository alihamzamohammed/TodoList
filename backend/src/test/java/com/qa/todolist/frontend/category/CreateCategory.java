package com.qa.todolist.frontend.category;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCategory {

    private static String frontendURL = "http://localhost:5500/frontend/";

    public static Boolean create(String title, WebDriver driver) {
        driver.get(frontendURL + "createCategory.html");
        WebElement titleInput = driver.findElement(By.id("title-input"));
        titleInput.sendKeys(title);
        WebElement createButton = driver.findElement(By.id("create-button"));
        createButton.click();
        By response = By.id("response");
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(response));
        return driver.findElement(response).getCssValue("color").equals("green");
    }

    public static Boolean reset(String title, WebDriver driver) {
        driver.get(frontendURL + "createCategory.html");
        WebElement titleInput = driver.findElement(By.id("title-input"));
        titleInput.sendKeys(title);
        WebElement resetButton = driver.findElement(By.id("reset-button"));
        resetButton.click();
        return titleInput.getAttribute("value").equals("");
    }

    public static Boolean discard(String title, WebDriver driver) {
        driver.get(frontendURL + "createCategory.html");
        WebElement titleInput = driver.findElement(By.id("title-input"));
        titleInput.sendKeys(title);
        WebElement discardButton = driver.findElement(By.id("discard-button"));
        discardButton.click();
        return driver.getCurrentUrl().endsWith("index.html");
    }
}
