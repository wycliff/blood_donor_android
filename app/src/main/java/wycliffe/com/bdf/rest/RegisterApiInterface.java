package wycliffe.com.bdf.rest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import wycliffe.com.bdf.model.RegisterResponseModel;

public interface RegisterApiInterface {

    @FormUrlEncoded
    @POST("register/")
    Call<RegisterResponseModel> getRegistered(@Field("email") String email, @Field("password") String password,
                                              @Field("full_name") String full_name, @Field("phone") String phone,
                                              @Field("first_time_donor") String ftd, @Field("blood_type") String blood_type,
                                              @Field("rhesus_factor") String rhesus, @Field("age") String age,
                                              @Field("current_location") String current_location, @Field("gender") String gender,
                                              @Field("weight") String weight);
}
