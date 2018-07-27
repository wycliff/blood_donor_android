package wycliffe.com.bdf.rest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import wycliffe.com.bdf.model.LoginModel;
import wycliffe.com.bdf.model.RegisterResponseModel;

public interface RegisterApiInterface {


    public interface LoginApiInterface {

        @FormUrlEncoded
        @POST("register/") //the endpoint.

            //return value is always a parameterized Call<T> object
        Call<RegisterResponseModel> getLogged(@Field("email") String email, @Field("password") String password);


    }
}
