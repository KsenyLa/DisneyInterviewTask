package com.disney.interview.pages;

import com.disney.interview.models.RegisterModel;
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

    private By logOutButton = By.cssSelector("div.popover.popover-bottom a[data-key-code='83']");

    private By continueButton = By.cssSelector("button.btn.btn-primary");

    public ShopPage register(RegisterModel model) throws InterruptedException {
        getElement(firstName).sendKeys(model.firstName);
        getElement(lastName).sendKeys(model.lastName);
        getElement(email).sendKeys(model.email);
        getElement(newPassword).sendKeys(model.password);
        getElement(verifyPassword).sendKeys(model.password);
        getElement(dateOfBirth).sendKeys(model.birthday);
        System.out.println("Register form filled");
        clickRegister();
        System.out.println("Going to click continue");
        Thread.sleep(500);
        clickContinue();
        System.out.println("Waiting page...");
        //here will be very long wait
        Thread.sleep(500);
        System.out.println("Go to parent frame");

        driver.switchTo().parentFrame();
        getElement(logOutButton, 30);
        System.out.println("Looks like we found Sign Out");
        Thread.sleep(500);
        return new ShopPage(driver);
    }

    public void clickRegister() {
        getElement(createAccountButton).click();
    }
    public void clickContinue() {
        retryingFindClick(continueButton);
    }

}
