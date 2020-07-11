package com.disney.interview.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By login = By.cssSelector("div.field.field-username-email.badgeable input");
    private By password = By.cssSelector("div.field.field-password.badgeable input");
    private By signInButton = By.cssSelector("section.workflow-login button.btn-submit");

    public void clickSignIn() {
        getElement(signInButton).click();
    }
}
