package com.disney.interview.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(By element) {
        return getVisibleElement(element, 10);
    }

    public WebElement getVisibleElement(By element, int secondsToWait) {
        ExpectedCondition<Boolean> condition = ExpectedConditions.and(
                ExpectedConditions.presenceOfElementLocated(element),
                ExpectedConditions.elementToBeClickable(element),
                ExpectedConditions.visibilityOfElementLocated(element));

        return getElementByConditions(element, secondsToWait, condition);
    }

    public WebElement getElement(By element, int secondsToWait) {
        ExpectedCondition<Boolean> condition = ExpectedConditions.and(
                ExpectedConditions.presenceOfElementLocated(element),
                ExpectedConditions.elementToBeClickable(element));

        return getElementByConditions(element, secondsToWait, condition);
    }

    public WebElement getElementByConditions(By element, int secondsToWait, ExpectedCondition<Boolean> conditions) {
        WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
        wait.until(conditions);

        return driver.findElement(element);
    }

    public boolean retryingFindClick(By by) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                //driver.findElement(by).click();
                getElement(by).click();
                result = true;
                break;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException: " + e.getMessage());
            }
            attempts++;
        }
        return result;
    }
}