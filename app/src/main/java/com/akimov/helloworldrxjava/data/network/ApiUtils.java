package com.akimov.helloworldrxjava.data.network;

public class ApiUtils {
    private static final ApiUtils ourInstance = new ApiUtils();

    public static ApiUtils getInstance() {
        return ourInstance;
    }

    private ApiUtils() {
    }

    //todo: вынести в константы или SharedPreferences
    public static final String BASE_URL = "https://query1.finance.yahoo.com/v7/finance/";
    public static final String LOCAL_BASE_URL = "http://192.168.1.101:5000/";


}
