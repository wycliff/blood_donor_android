package wycliffe.com.bdf.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Wycliffe on 12/27/2017.
 */

public class SessionManager {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "LOGIN_PREF";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // Secret code sent during each transaction
    public static final String KEY_SECRET_CODE  = "secretCode";

    // You can at this point add the rest of the details that the user will enter


    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE); //creates the sharedPreference
        editor = pref.edit(); // initializes the editor


    }

    /**
     * Create login session
     * */
    //This function simply stores login status(true), and agent name and the secret code in shared preferences.

    public void createLoginSession(String name, String secret ){

        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref takes key and value
        editor.putString(KEY_NAME, name );

        editor.putString(KEY_SECRET_CODE, secret);

        // commit changes
        editor.commit();
    }


    //In order to get the stored preferences data, I added a function called getAgentDetails() with the following code.
    //The following function will read shared preferences and returns user data in HashMap. Keys initalized in the SharedPreference Class

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getDonorDetails(){

        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        //  secret key
        user.put(KEY_SECRET_CODE, pref.getString(KEY_SECRET_CODE, null));

        // return user
        return user;
    }


    // can call in all other Activities to check user login status.
    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */

    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, Login.class);

            // Closing all the Activities / i did manual mostly.
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Starting Login Activity
            _context.startActivity(i);
        }

    }

    // Add a function called logoutUser()
    // to clear all the data from shared preferences. Call this function when you want to logout the user.
    // ( YOU CAN PUT TIMER EACH TIME THE AGENT IS IDLE
    // THEN CALL THIS METHOD TO LOG HIM / HER OUT). Confirm how to check for idleness...
    // This function clears all session data and redirect the user to LoginActivity

    /**
     * Clear session details
     * */

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, Login.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Starting Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false); // get the boolean status otherwise give false
    }

}
