package com.disney.interview.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShopPage extends BasePage{
    public ShopPage(WebDriver driver) {
        super(driver);
    }

    private By loginButton = By.cssSelector(".signin-or-signup__button");
    private By profileMenuButton = By.cssSelector(".user-message");
    private By myAccountItem = By.cssSelector("div.popover.popover-bottom a[data-key-code='77']");

    public LoginPage clickLogin() throws InterruptedException {
        getElement(loginButton).click();

        return new LoginPage(driver);
    }

    public String readWelcomeMessage() {
        return getElement(profileMenuButton).getText().trim();
    }
}
