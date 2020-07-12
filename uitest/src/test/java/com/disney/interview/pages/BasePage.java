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

    protected WebElement getElement(By element) {
        return getElement(element, 20);
    }

    protected WebElement getElementVisible(By element, int secondsToWait) {
        ExpectedCondition<Boolean> condition = ExpectedConditions.and(
                ExpectedConditions.presenceOfElementLocated(element),
                ExpectedConditions.visibilityOfElementLocated(element));

        return getElementByConditions(element, secondsToWait, condition);
    }

    public WebElement getElement(By element, int secondsToWait) {
        ExpectedCondition<Boolean> condition = ExpectedConditions.and(
                ExpectedConditions.presenceOfElementLocated(element),
                ExpectedConditions.elementToBeClickable(element));

        return getElementByConditions(element, secondsToWait, condition);
    }

    protected WebElement getElementByConditions(By element, int secondsToWait, ExpectedCondition<Boolean> conditions) {
        WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
        wait.until(conditions);

        return driver.findElement(element);
    }

    protected void waitUntilDisappear(By element, int secondsToWait) {
        new WebDriverWait(driver, secondsToWait)
                .until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    protected void waitLoader(By loader) {
        System.out.println("Looking for loader");
        getElement(loader);
        System.out.println("Loader located, will wait until loader complete");
        waitUntilDisappear(loader, 30);
        System.out.println("Loader disappeared");
    }

    /**
     * Workaround to fight Stale Element problem
     *
     * @param by
     * @return
     */
    protected boolean retryingFindClick(By by) {
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