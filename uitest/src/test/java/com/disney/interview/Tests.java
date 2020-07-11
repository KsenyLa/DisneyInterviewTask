package com.disney.interview;

import org.testng.annotations.Test;

public class Tests {
    @Test
    public void registerUser() {
        System.out.println("registerUser");
    }


    @Test(dependsOnMethods = { "registerUser" })
    public void loginAndCheckInfo() {
        System.out.println("loginAndCheckInfo");
    }

}
