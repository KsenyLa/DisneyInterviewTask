package com.disney.interview.util;

import com.disney.interview.models.RegisterModel;

import java.util.Random;

public class ModelFactory {
    /**
     * Generator of user data and random email address
     * @return Generated RegisterModel
     */
    public static RegisterModel createRegisterModel() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(100000);

        RegisterModel model = new RegisterModel();
        model.firstName = "Ksen" + randomNumber;
        model.lastName = "Qa";
        model.birthday = "10/20/2000";
        model.password = "blaTRA234-34O";
        model.email = "fepager583+" + randomNumber + "@djemail.net";

        return model;
    }
}