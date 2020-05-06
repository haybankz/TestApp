package com.example.testapp.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static final String BASE_URL = "https://api.themoviedb.org/3/"; //base url
    private static HttpLoggingInterceptor logging;
    private static OkHttpClient.Builder httpClient;
    private static GsonBuilder gsonBuilder = new GsonBuilder();
    private static Gson gson = gsonBuilder.create();


    /*
     * returns a retrofit object*/
    private static Retrofit getClient() {

        logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient = new OkHttpClient.Builder();
        httpClient.writeTimeout(50, TimeUnit.SECONDS); // set write timeout time to 50seconds
        httpClient.readTimeout(50, TimeUnit.SECONDS);   // set read timeout time to 50seconds
        httpClient.connectTimeout(30, TimeUnit.SECONDS);    // set connect timeout time to 50seconds

        httpClient.retryOnConnectionFailure(false);     //set retryOnConnectionFailure to false, so that the client does not repeat failed requests
//        httpClient.addInterceptor(logging);
        OkHttpClient client = httpClient.build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson)) // add a gsonConverter to parse the api call response to Java Object
                .build();


    }

    public static <S> S createService(Class<S> serviceClass) {
        return getClient().create(serviceClass);
    }
}
