package com.qa.todolist.frontend.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReadCategory {

    public static WebElement findCategory(int id, WebDriver driver) {
        By element = By.cssSelector("#category-" + id);
        driver.navigate().refresh();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element);
    }

    public static String findId(int id, WebDriver driver) {
        return findCategory(id, driver).findElement(By.cssSelector(".category-id h4")).getText();
    }

    public static String findName(int id, WebDriver driver) {
        return findCategory(id, driver).findElement(By.cssSelector(".category-name h3")).getText();
    }
}
