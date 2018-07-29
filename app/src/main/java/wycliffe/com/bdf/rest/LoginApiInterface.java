package wycliffe.com.bdf.rest;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import wycliffe.com.bdf.model.LoginModel;
import wycliffe.com.bdf.model.LoginResponseModel;


/**
 * Created by Wycliffe on 8/15/2017.
 */

public interface LoginApiInterface {

    @FormUrlEncoded
    @POST("login/") //the endpoint.

        //return value is always a parameterized Call<T> object
    Call<LoginResponseModel> getLogged(@Field("email") String email, @Field("password") String password);


}
