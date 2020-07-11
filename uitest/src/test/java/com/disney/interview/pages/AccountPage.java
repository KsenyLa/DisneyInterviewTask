package com.disney.interview.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage{
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    private By accountName = By.cssSelector("div.account-name");
    private By accountEmail = By.cssSelector("div.account-email");
}
