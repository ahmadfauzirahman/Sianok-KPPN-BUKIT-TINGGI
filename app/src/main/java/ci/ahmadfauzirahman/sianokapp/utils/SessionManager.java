package ci.ahmadfauzirahman.sianokapp.utils;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ci.ahmadfauzirahman.sianokapp.Model.AkunModel;

public class SessionManager {

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    private Context _context;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String USER_ID = "userID";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NAMA = "nama";
    public static final String STATUS = "status";
    public static final String EMAIL = "EMAIL";
    public static final String TELEPON = "telepon";

    public static final String IS_GET_LOCATION = "isLogged";
    public static final String ALAMAT = "alamat";
    public static final String LAT = "lat";
    public static final String LANG = "lang";

    public static final String penToken = "penToken";


    public Context get_context() {
        return _context;
    }

    //constuctor
    public SessionManager(Context context) {
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(AkunModel user) {
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(USER_ID, user.getUserID());
        editor.putString(USERNAME, user.getUsername());
        editor.putString(PASSWORD, user.getPassword());
        editor.putString(NAMA, user.getNama());
        editor.putString(EMAIL, user.getEmail());
        editor.putString(STATUS, user.getStatus());
        editor.putString(TELEPON, user.getTelepon());
        editor.commit();
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(USER_ID, sharedPreferences.getString(USER_ID, null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(PASSWORD, sharedPreferences.getString(PASSWORD, null));
        user.put(NAMA, sharedPreferences.getString(NAMA, null));
        user.put(STATUS, sharedPreferences.getString(STATUS, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(TELEPON, sharedPreferences.getString(TELEPON, null));
        return user;
    }

    public String getToken() {
        return sharedPreferences.getString(penToken, "");
    }

    public void setLocation(String alamat, String lat, String lang) {
        editor.putBoolean(IS_GET_LOCATION, true);
        editor.putString(ALAMAT, alamat);
        editor.putString(LAT, lat);
        editor.putString(LANG, lang);
        editor.commit();
    }

    public HashMap<String, String> getLocation() {
        HashMap<String, String> location = new HashMap<>();
        location.put(ALAMAT, sharedPreferences.getString(ALAMAT, null));
        location.put(LAT, sharedPreferences.getString(LAT, null));
        location.put(LANG, sharedPreferences.getString(LANG, null));
        return location;
    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences
//        editor.clear();
        editor.putBoolean(IS_LOGGED_IN, false);
        editor.putString(USERNAME,null);
        editor.putString(PASSWORD,null);
        editor.putString(NAMA,null);
        editor.putString(STATUS,null);
        editor.putString(EMAIL,null);
        editor.commit();

    }

    public void saveToken(String fcm) {
        editor.putString(penToken, fcm);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

    public boolean isGetLocation() {
        return sharedPreferences.getBoolean(IS_GET_LOCATION, false);
    }
}
