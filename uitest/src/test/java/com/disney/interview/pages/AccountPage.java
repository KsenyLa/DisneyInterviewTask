package com.disney.interview.pages;

import com.disney.interview.models.AccountInfoModel;
import com.disney.interview.models.RegisterModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage extends BasePage{
    private By accountName = By.cssSelector("div.account-name");
    private By accountEmail = By.cssSelector("div.account-email");
    private By loader = By.cssSelector("div.radial-loader-bg");

    public AccountPage(WebDriver driver) throws InterruptedException {
        super(driver);
        Thread.sleep(500);

        System.out.println("Wait for loader start");
        getElement(loader);
        System.out.println("Loader started complete");
        System.out.println("Wait until loader complete");
        waitUntilDisappear(loader, 30);
        System.out.println("Loader stopped");
    }

    public AccountInfoModel readUserInfo(){
        AccountInfoModel result = new AccountInfoModel();
        result.name = getElement(accountName).getText();
        result.email = getElement(accountEmail).getText();

        return result;
    }
}
