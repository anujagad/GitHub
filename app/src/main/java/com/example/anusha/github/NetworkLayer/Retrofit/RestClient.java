package com.example.anusha.github.NetworkLayer.Retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static final String BASE_URL = "https://api.github.com/";
    private ServicesInterface mGithubApiInterface;


    private static ServicesInterface setUpRestClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(initLogger().build())
                .build();

        return retrofit.create(ServicesInterface.class);

    }

    public static ServicesInterface getAdapter() {
        return setUpRestClient();
    }

    private static OkHttpClient.Builder initLogger(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        return httpClient;
    }
}
