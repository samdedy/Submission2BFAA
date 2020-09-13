package id.sam.submission2bfaa.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static String BASE_URL = " https://api.github.com/";
    public static String API_KEY = "e90d44e430119303a084ae702255a673d94decf7";

    public static ApiService getApiService(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "token "+API_KEY)
                        .build();
                return chain.proceed(newRequest);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        //OkHttpClient client = new OkHttpClient.Builder().build();
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(String.class, new StringConverter());
        gb.serializeNulls();
        // gb.excludeFieldsWithoutExposeAnnotation();
        Gson gson = gb.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        ApiService service = retrofit.create(ApiService.class);
        return service;
    }
}
