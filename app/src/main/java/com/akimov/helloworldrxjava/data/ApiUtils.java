package com.akimov.helloworldrxjava.data;

public class ApiUtils {
    private static final ApiUtils ourInstance = new ApiUtils();

    public static ApiUtils getInstance() {
        return ourInstance;
    }

    private ApiUtils() {
    }


    public static final String BASE_URL = "https://query1.finance.yahoo.com/v7/finance/";


}
