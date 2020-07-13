package com.disney.interview.pages;

import com.disney.interview.models.AccountInfoModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage{
    private By accountName = By.cssSelector("div.account-name");
    private By accountEmail = By.cssSelector("div.account-email");
    private By loader = By.cssSelector("div.radial-loader-bg");

    public AccountPage(WebDriver driver) throws InterruptedException {
        super(driver);
        waitLoader(loader);
    }

    public AccountInfoModel readUserInfo(){
        AccountInfoModel result = new AccountInfoModel();
        result.name = getElement(accountName).getText();
        result.email = getElement(accountEmail).getText();

        return result;
    }
}
