package com.qa.todolist.frontend.category;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditCategory {

    public static Boolean update(String name, WebDriver driver, String frontendURL) {
        driver.get(frontendURL + "editCategory.html?id=" + 1);
        WebElement nameInput = driver.findElement(By.id("name-input"));
        nameInput.clear();
        nameInput.sendKeys(name);
        WebElement createButton = driver.findElement(By.id("update-button"));
        createButton.click();
        By response = By.id("response");
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(response));
        return driver.findElement(response).getText().startsWith("Category successfully updated!");
    }

    public static Boolean reset(String name, WebDriver driver, String frontendURL) {
        driver.get(frontendURL + "editCategory.html?id=" + 1);
        WebElement nameInput = driver.findElement(By.id("name-input"));
        nameInput.sendKeys(name);
        WebElement resetButton = driver.findElement(By.id("reset-button"));
        resetButton.click();
        return nameInput.getAttribute("value").equals("");
    }

    public static Boolean cancel(WebDriver driver, String frontendURL) {
        driver.get(frontendURL + "editCategory.html?id=" + 1);
        WebElement cancelButton = driver.findElement(By.id("cancel-button"));
        cancelButton.click();
        return driver.getCurrentUrl().endsWith("index.html");
    }

    public static Boolean delete(WebDriver driver, String frontendURL) {
        driver.get(frontendURL + "editCategory.html?id=" + 1);
        WebElement deleteButton = driver.findElement(By.id("delete-button"));
        deleteButton.click();
        By response = By.id("response");
        new WebDriverWait(driver, 7).until(ExpectedConditions.visibilityOfElementLocated(response));
        return driver.findElement(response).getText().equals("Category successfully deleted!");
    }
}
