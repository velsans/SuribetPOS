package com.suribetpos.main.data.api;

import android.content.Context;

import com.suribetpos.main.utils.Common;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofitHttpExternal = null, retrofitHttpLocalORLive = null, retrofitHttpLanguage = null, retrofitCardValidation = null;
    private static ApiClient instance = null;
    Context context;
    // Keep your services here, build them in buildRetrofit method later
    private ApiInterface apiInterface;

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    // Build retrofit once when creating a single instance
    private ApiClient() {
        getApiInterface();
        try {
            getApiLanguageInterface();
            getCardApiInterface();
        } catch (Exception ex) {

        }
    }

    public ApiInterface getUserService() {
        return this.apiInterface;
    }

    public static ApiInterface getApiInterface() {
        if (retrofitHttpLocalORLive == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    //.addInterceptor(ChuckerInterceptor(get))
                    .build();

            retrofitHttpLocalORLive = new Retrofit.Builder()
                    .baseUrl(ServiceURL.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofitHttpLocalORLive.create(ApiInterface.class);
    }

    public static ApiInterface getApiLanguageInterface() {
        try {
            if (retrofitHttpLanguage == null) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build();
                retrofitHttpLanguage = new Retrofit.Builder()
                        .baseUrl(ServiceURL.Language_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();
            }
        } catch (Exception ex) {

        }
        return retrofitHttpLanguage.create(ApiInterface.class);
    }

    public static ApiInterface getCardApiInterface() {
        if (retrofitCardValidation == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    //.addInterceptor(ChuckerInterceptor(get))
                    .build();

            retrofitCardValidation = new Retrofit.Builder()
                    .baseUrl(Common.CardValidation_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofitCardValidation.create(ApiInterface.class);
    }

}
