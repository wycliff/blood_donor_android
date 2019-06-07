package wycliffe.com.bdf.other;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import wycliffe.com.bdf.model.LoginResponseModel;
import wycliffe.com.bdf.view.activity.Login;

/**
 * Created by Wycliffe on 12/27/2017.
 */

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN_PREF";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_TYPE = "blood_type";
    public static final String KEY_WEIGHT = "weight";
    public static final String KEY_LOCATION = "current_location";
    public static final String KEY_RHESUS = "rhesus_factor";
    public static final String KEY_FTD = "first_time_donor";
    public static final String KEY_AGE = "age";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(LoginResponseModel regModel) {

        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_NAME, regModel.getFullName());
        editor.putString(KEY_AGE, regModel.getAge().toString());
        editor.putString(KEY_EMAIL, regModel.getEmail());
        editor.putString(KEY_FTD, regModel.getFirstTimeDonor().toString());
        editor.putString(KEY_GENDER, regModel.getGender());
        editor.putString(KEY_LOCATION, regModel.getCurrentLocation().toString());
        editor.putString(KEY_PHONE, regModel.getPhone());
        editor.putString(KEY_RHESUS, regModel.getRhesusFactor().toString());
        editor.putString(KEY_TYPE, regModel.getBloodType());
        editor.putString(KEY_WEIGHT, regModel.getWeight().toString());
        editor.commit();
    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {

        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_PHONE, pref.getString(KEY_PHONE, null));
        user.put(KEY_WEIGHT, pref.getString(KEY_WEIGHT, null));
        user.put(KEY_AGE, pref.getString(KEY_AGE, null));
        user.put(KEY_TYPE, pref.getString(KEY_TYPE, null));
        user.put(KEY_RHESUS, pref.getString(KEY_RHESUS, null));
        user.put(KEY_LOCATION, pref.getString(KEY_LOCATION, null));
        user.put(KEY_FTD, pref.getString(KEY_FTD, null));
        user.put(KEY_GENDER, pref.getString(KEY_GENDER, null));

        return user;
    }

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */

    public void checkLogin() {
        if (!this.isLoggedIn()) {
            Intent i = new Intent(_context, Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    /**
     * Clear session details
     */

    public void logoutUser() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     **/
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
