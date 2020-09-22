package com.example.project2.repository.web.service;

import com.example.project2.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class ServiceGenerator {

    private static final String BASE_URL = "https://rfidis.raf.edu.rs/raspored/";
    private static final int READ_TIMEOUT = 10;

    private static final HttpLoggingInterceptor sHttpLoggingInterceptor =
            new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

    private static OkHttpClient.Builder sHttpClientBuilder = new OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(sHttpLoggingInterceptor);

    private static OkHttpClient sOkHttpClient = sHttpClientBuilder.build();

    private static Retrofit.Builder sBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit sRetrofit = sBuilder.client(sOkHttpClient).build();

    public static  <S> S createService(Class<S> serviceClass) {
        return sRetrofit.create(serviceClass);
    }
}
