package com.disney.interview.pages;

import com.disney.interview.models.RegisterModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    private By email = By.cssSelector("div.field.field-username-email.badgeable input");
    private By password = By.cssSelector("div.field.field-password.badgeable input");
    private By signInButton = By.cssSelector("section.workflow-login button.btn-submit");

    private By loader = By.cssSelector("svg.symbol-spinner");

    public LoginPage(WebDriver driver) throws InterruptedException {
        super(driver);
        Thread.sleep(200);
        driver.switchTo().frame("disneyid-iframe");
    }

    private By createAccount = By.cssSelector(".btn-secondary.ng-isolate-scope");

    public ShopPage signIn(RegisterModel model) throws InterruptedException {
        getElement(email).sendKeys(model.email);
        getElement(password).sendKeys(model.password);
        System.out.println("Click sign in");

        return clickSignIn();
    }

    public ShopPage clickSignIn() throws InterruptedException {
        getElement(signInButton).click();

        System.out.println("Go to parent frame");
        driver.switchTo().parentFrame();

        waitLoader(loader);

        return new ShopPage(driver);
    }

    public RegisterPage clickCreateAccount() throws InterruptedException {

        WebElement element = driver.findElement(createAccount);
        element.click();

        return new RegisterPage(driver);
    }
}
