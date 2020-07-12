package com.disney.interview.pages;

import com.disney.interview.models.RegisterModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) throws InterruptedException {
        super(driver);
        Thread.sleep(200);
        driver.switchTo().frame("disneyid-iframe");
    }

    private By email = By.cssSelector("div.field.field-username-email.badgeable input");
    private By password = By.cssSelector("div.field.field-password.badgeable input");
    private By signInButton = By.cssSelector("section.workflow-login button.btn-submit");

    private By createAccount = By.cssSelector(".btn-secondary.ng-isolate-scope");

    public ShopPage signIn(RegisterModel model) {
        getElement(email).sendKeys(model.email);
        getElement(password).sendKeys(model.password);

        return clickSignIn();
    }

    public ShopPage clickSignIn() {
        getElement(signInButton).click();

        return new ShopPage(driver);
    }

    public RegisterPage clickCreateAccount() throws InterruptedException {

        WebElement element = driver.findElement(createAccount);
        element.click();

        return new RegisterPage(driver);
    }
}
