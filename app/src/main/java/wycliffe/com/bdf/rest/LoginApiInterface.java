package wycliffe.com.bdf.rest;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import wycliffe.com.bdf.model.LoginResponseModel;


/**
 * Created by Wycliffe on 8/15/2017.
 */

// Interface
public interface LoginApiInterface {
    @FormUrlEncoded
    //the endpoint.
    @POST("login/")  // alternatively , you can use @url
    /**
     *  Return value is always a parameterized Call<T> object or some other parametarized Object<T>
     *  you can use the @Body annotation to send jsonObject with a lot of data. ( @Field is for single key , it is better here to use @Body)
     *  Instead of return type Call you can also be an observable type if you are using RXJava or type MutableLiveData if using live data
     *  For Kotlin and live data : https://proandroiddev.com/demystifying-retrofit-network-call-with-kotlin-livedata-27437439137a
     *  For RXJava basics : https://medium.com/mindorks/rxjava-operator-map-vs-flatmap-427c09678784
     */
    Call<LoginResponseModel> getLogged(@Field("email") String email, @Field("password") String password);
}
