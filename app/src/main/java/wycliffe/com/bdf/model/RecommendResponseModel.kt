package wycliffe.com.bdf.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RecommendResponseModel {

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("full_name")
    @Expose
    var fullName: String? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null
    @SerializedName("first_time_donor")
    @Expose
    var firstTimeDonor: Boolean? = null

    @SerializedName("blood_type")
    @Expose
    var bloodType: String? = null

    @SerializedName("rhesus_factor")
    @Expose
    var rhesusFactor: Boolean? = null

    @SerializedName("age")
    @Expose
    var age: Int? = null

    @SerializedName("current_location")
    @Expose
    var currentLocation: Int? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("weight")
    @Expose
    var weight: Int? = null

}
