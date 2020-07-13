package com.disney.interview.util;

import com.disney.interview.models.RegisterModel;

import java.io.IOException;

public interface DataProvider {
    /**
     * Retrieves RegisterModel from storage
     * @return Loaded RegisterModel
     */
    RegisterModel getRegisterModel() throws IOException;

    /**
     * Saves RegisterModel from storage
     * @param model RegisterModel for saving
     */
    void setRegisterModel(RegisterModel model) throws IOException;
}
