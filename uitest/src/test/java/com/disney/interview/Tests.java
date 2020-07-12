package com.disney.interview;

import com.disney.interview.models.AccountInfoModel;
import com.disney.interview.models.LoginModel;
import com.disney.interview.models.RegisterModel;
import com.disney.interview.pages.*;
import com.disney.interview.util.DataProviderFactory;
import com.disney.interview.util.DataProviderType;
import com.disney.interview.util.ModelFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.disney.interview.util.DataProvider;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

    @Test
    public void registerUser() throws IOException, InterruptedException {
        System.out.println("registerUser");
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

        //checking that first name of user match expected
        Assert.assertEquals(firstName, registerModel.firstName);

        provider.setRegisterModel(registerModel);
    }


    @Test(dependsOnMethods = { "registerUser" })
    //@Test
    public void loginAndCheckInfo() throws IOException, InterruptedException {
        System.out.println("loginAndCheckInfo");
        RegisterModel registerModel = provider.getRegisterModel();
        String expectedFullName = String.format("%s %s", registerModel.firstName, registerModel.lastName);

        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        ShopPage shopPage = homePage.clickShop();
        LoginPage loginPage = shopPage.clickLogin();
        shopPage = loginPage.signIn(registerModel);
        AccountPage accountPage = shopPage.clickMyAccount();
        AccountInfoModel accountInfoModel = accountPage.readUserInfo();

        Assert.assertEquals(accountInfoModel.email, registerModel.email, "Email is matched");
        Assert.assertEquals(accountInfoModel.name, expectedFullName, "Name is matched");
        System.out.println("Profile values matches expected!");
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        driver.quit();
    }
}
