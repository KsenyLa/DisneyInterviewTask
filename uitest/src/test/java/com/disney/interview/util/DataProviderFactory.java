package com.disney.interview.util;

import com.disney.interview.models.RegisterModel;

public class DataProviderFactory {

    /**
     * Creates data provider
     * @param providerName desired provider name
     * @return data provider instance
     */
    public static DataProvider create(DataProviderType providerName) {
        switch (providerName) {
            case Excel:
                return new ExcelProvider();
            case Json:
                // I prefer to use JSON instead of Excel. Test task requires using Excel. So I created factory
                // to demonstrate a flexible way to potentially change the Data Provider.
                throw new UnsupportedOperationException("JSON data provider not implemented");
            default:
                throw new IllegalArgumentException("Invalid data provider");
        }
    }
}

