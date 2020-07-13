package com.disney.interview.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ShopPage extends BasePage{
    private By loginButton = By.cssSelector(".signin-or-signup__button");
    private By profileMenuButton = By.cssSelector("button.user-message");
    private By myAccountItem = By.cssSelector("div.popover.popover-bottom a[data-key-code='77']");

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickLogin() throws InterruptedException {
        getElement(loginButton).click();

        return new LoginPage(driver);
    }

    public String readWelcomeMessage() {
        return getElement(profileMenuButton).getText().trim();
    }

    public AccountPage clickMyAccount() throws InterruptedException {
        System.out.println("Move to Menu button with User Name");
        WebElement profileMenu = getElementVisible(profileMenuButton, 30);

        Actions actions = new Actions(driver);
        actions.moveToElement(profileMenu).perform();

        System.out.println("Waiting for My Account to be visible");
        getElementVisible(myAccountItem, 10).click();

        return new AccountPage(driver);
    }
}
