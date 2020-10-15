package com.example.ta.API;

public class UtilsApi {
    public static final String BASE_URL_API = "http://192.168.0.149:8000";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
