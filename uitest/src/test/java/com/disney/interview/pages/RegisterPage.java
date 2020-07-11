package com.disney.interview.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage{
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private By firstName = By.name("firstName");
    private By lastName = By.name("lastName");
    private By email = By.name("email");
    private By newPassword = By.name("newPassword");
    private By verifyPassword = By.name("verifyPassword");
    private By dateOfBirth = By.name("dateOfBirth");
    private By createAccountButton = By.cssSelector("section.section-submit button");

    public void clickRegister() {
        getElement(createAccountButton).click();
    }
}
