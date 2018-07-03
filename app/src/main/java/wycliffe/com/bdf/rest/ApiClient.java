package wycliffe.com.bdf.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wycliffe on 12/27/2017.
 */

public class ApiClient {


    public static final String BASE_URL ="http://127.0.0.1:8000/myrest/users/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient(){
        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        // Return type is Retrofit
        return retrofit;
    }
}
































































































































































































