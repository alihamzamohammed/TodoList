package com.qa.todolist.frontend.settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SettingsPage {

    public static Boolean getUrl(String url, WebDriver driver, String frontendURL) {
        driver.get(frontendURL + "settings.html");
        WebElement settings = driver.findElement(By.id("backend-link"));
        return settings.getAttribute("value").equals(url);
    }

    public static void setUrl(String url, WebDriver driver, String frontendURL) {
        driver.get(frontendURL + "settings.html");
        WebElement settings = driver.findElement(By.id("backend-link"));
        settings.clear();
        settings.sendKeys(url);
        WebElement saveButton = driver.findElement(By.id("save-button"));
        saveButton.click();
    }
}
