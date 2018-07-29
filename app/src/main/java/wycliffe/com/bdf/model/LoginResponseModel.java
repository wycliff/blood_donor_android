package wycliffe.com.bdf.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponseModel {

        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("full_name")
        @Expose
        private String fullName;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("first_time_donor")
        @Expose
        private Boolean firstTimeDonor;
        @SerializedName("blood_type")
        @Expose
        private String bloodType;
        @SerializedName("rhesus_factor")
        @Expose
        private Boolean rhesusFactor;
        @SerializedName("age")
        @Expose
        private Integer age;
        @SerializedName("current_location")
        @Expose
        private Integer currentLocation;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("weight")
        @Expose
        private Integer weight;
        private final static long serialVersionUID = 736699398820943002L;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Boolean getFirstTimeDonor() {
            return firstTimeDonor;
        }

        public void setFirstTimeDonor(Boolean firstTimeDonor) {
            this.firstTimeDonor = firstTimeDonor;
        }

        public String getBloodType() {
            return bloodType;
        }

        public void setBloodType(String bloodType) {
            this.bloodType = bloodType;
        }

        public Boolean getRhesusFactor() {
            return rhesusFactor;
        }

        public void setRhesusFactor(Boolean rhesusFactor) {
            this.rhesusFactor = rhesusFactor;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getCurrentLocation() {
            return currentLocation;
        }

        public void setCurrentLocation(Integer currentLocation) {
            this.currentLocation = currentLocation;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }


}
