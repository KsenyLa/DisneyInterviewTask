package com.disney.interview;

import com.disney.interview.models.AccountInfoModel;
import com.disney.interview.models.RegisterModel;
import com.disney.interview.pages.*;
import com.disney.interview.util.DataProvider;
import com.disney.interview.util.DataProviderFactory;
import com.disney.interview.util.DataProviderType;
import com.disney.interview.util.ModelFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Tests {
    private DataProvider provider;
    private static WebDriver driver;

    @BeforeClass
    public void setUp(){
        provider = DataProviderFactory.create(DataProviderType.Excel);
        System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
    }

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts();
        driver.manage().window().maximize();
        Thread.sleep(1000);
    }

    /**
     * Given I'm on the Home page (www.disney.com)
     *     And I navigate to the Shop page
     *     When I click on "Sign In| Sign Up"
     *     And I select "Create an Account"
     *     And I enter First Name, Last Name, Email address, Password, Verify Password, Birth Date and click on "Create Account"
     *     Then account should be created successfully
     *     And expected First Name should be displayed as a name of Menu element
     */
    @Test
    public void whenUserRegistered_thenUsernameShouldBeDisplayed() throws IOException, InterruptedException {
        System.out.println("Test started: whenUserRegistered_thenUsernameShouldBeDisplayed");
        RegisterModel registerModel = ModelFactory.createRegisterModel();
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        ShopPage shopPage = homePage.clickShop();
        LoginPage loginPage = shopPage.clickLogin();
        RegisterPage registerPage = loginPage.clickCreateAccount();
        shopPage = registerPage.register(registerModel);
        String welcome = shopPage.readWelcomeMessage();
        System.out.println("Welcome message: " + welcome);
        String firstName = welcome.substring(0, welcome.length()-1).replace("Hi ","");

        //Checking that displayed First Name on Menu element matches expected name of user
        Assert.assertEquals(firstName, registerModel.firstName);
        System.out.println("Username matches expected!");
        provider.setRegisterModel(registerModel);
    }

    /**
     * Given I'm on the Home page (www.disney.com)
     *     And I navigate to the Shop page
     *     When I log in as previously registered user with valid login and password
     *     And I go to "My Account"
     *     Then expected First Name, Last Name and Email address should be displayed under "Account Details"
     */
    @Test(dependsOnMethods = { "whenUserRegistered_thenUsernameShouldBeDisplayed" })
    //@Test
    public void whenPreviouslyRegisteredUserLogsIn_thenAccountDisplaysRegisteredInfo() throws IOException, InterruptedException {
        System.out.println("Test started: whenUserRegistered_thenUsernameShouldBeDisplayed");
        RegisterModel registerModel = provider.getRegisterModel();
        String expectedFullName = String.format("%s %s", registerModel.firstName, registerModel.lastName);

        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        ShopPage shopPage = homePage.clickShop();
        LoginPage loginPage = shopPage.clickLogin();
        shopPage = loginPage.signIn(registerModel);
        AccountPage accountPage = shopPage.clickMyAccount();
        AccountInfoModel accountInfoModel = accountPage.readUserInfo();

        Assert.assertEquals(accountInfoModel.email, registerModel.email, "Email matches expected");
        Assert.assertEquals(accountInfoModel.name, expectedFullName, "Full Name matches expected");
        System.out.println("Profile information matches expected");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
