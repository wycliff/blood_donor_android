package wycliffe.com.bdf.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import wycliffe.com.bdf.model.RecommendResponseModel;


public interface RecommendApiInterface {


    @FormUrlEncoded
    @POST("recommend/") //the endpoint.

        //return value is always a parameterized Call<T> object
    Call<ArrayList<RecommendResponseModel>> getRecommendations(
                                                    @Field("blood_type") String blood_type,
                                                    @Field("rhesus_factor") String rhesus, @Field("age") String age,
                                                    @Field("current_location") String current_location,@Field("gender") String gender,
                                                    @Field("weight") String weight );
}


