package com.disney.interview.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By newAddressClick = By.cssSelector(".button-md.button-primary.button-tall");

    public void clickAddAddress() {
        getElement(newAddressClick).click();
    }
}

