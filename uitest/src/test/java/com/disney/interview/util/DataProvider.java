package com.disney.interview.util;

import com.disney.interview.models.LoginModel;
import com.disney.interview.models.RegisterModel;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataProvider {
    LoginModel getLoginModel();
    void setLoginModel(LoginModel model);
    RegisterModel getRegisterModel() throws IOException;
    void setRegisterModel(RegisterModel model) throws IOException;
}
