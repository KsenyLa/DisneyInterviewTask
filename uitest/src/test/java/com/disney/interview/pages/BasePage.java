package com.disney.interview.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setWebDriver(WebDriver driver)
    {
        this.driver = driver;
    }

    public WebElement getElement(By element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(
                ExpectedConditions.and(
                        ExpectedConditions.presenceOfElementLocated(element),
                        ExpectedConditions.elementToBeClickable(element),
                        ExpectedConditions.visibilityOfElementLocated(element)
                        //ExpectedConditions.visibilityOf(driver.findElement(element))
                )
        );
        return driver.findElement(element);
    }
}

