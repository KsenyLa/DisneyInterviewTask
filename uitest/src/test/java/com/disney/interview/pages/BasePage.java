package com.disney.interview.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement getElement(By element) {
        return getElement(element, 20);
    }

    /**
     * Expected conditions for element to be present and visible
     * @return Found element
     */
    protected WebElement getElementVisible(By element, int secondsToWait) {
        return getElementByCondition(element, secondsToWait, ExpectedConditions.visibilityOfElementLocated(element));
    }

    /**
     * Expected conditions for element to be present and clickable
     * @return Found element
     */
    public WebElement getElement(By element, int secondsToWait) {
        return getElementByCondition(element, secondsToWait, ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait for element by selected expected condition
     * @param element Element
     * @param secondsToWait Waiting time maximum
     * @param condition Expected condition
     * @return
     */
    protected WebElement getElementByCondition(By element, int secondsToWait, ExpectedCondition<WebElement> condition) {
        new WebDriverWait(driver, secondsToWait)
                .until(condition);

        return driver.findElement(element);
    }

    /**
     * Wait for element by selected expected condition - invisibilityOfElementLocated
     */
    protected void waitUntilDisappear(By element, int secondsToWait) {
        new WebDriverWait(driver, secondsToWait)
                .until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    /**
     * Wait for loading overlay
     * @param loader Selector of overlay
     */
    protected void waitLoader(By loader) {
        System.out.println("Check if loading overlay is presented");
        getElement(loader);
        System.out.println("Loading overlay was located, wait until loading completes");
        waitUntilDisappear(loader, 30);
        System.out.println("Page loading finished");
    }

    /**
     * Workaround to solve stale Element problem if the Element no longer appears on the DOM of the page
     * @return If element was successfully clicked
     */
    protected boolean retryingFindClick(By by) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 2) {
            try {
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