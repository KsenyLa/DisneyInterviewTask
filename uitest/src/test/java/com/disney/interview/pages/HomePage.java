package com.disney.interview.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {
    private static final String URL = "https://www.disney.com/";
    private By shopLink = By.cssSelector("#goc-desktop-global li.goc-desktop.bar-dropdown a[aria-controls=Shop-dropdown]");
    private By overlay = By.cssSelector("section.overlayClose");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo() {
        System.out.println("Navigate to https://www.disney.com");
        driver.navigate().to(URL);
    }

    public ShopPage clickShop() throws InterruptedException {
        //Wait for overlay with advertisement. Overlay is in iFrame
        Thread.sleep(500);
        //Close overlay by clicking in random location
        Actions builder = new Actions(driver);
        builder.moveByOffset(10, 25).click().build().perform();

        getElement(shopLink).click();

        return new ShopPage(driver);
    }
}