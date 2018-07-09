package com.mc.eenadunap.views;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferenceData {
    // public static final String KEY_PREFS_NAME = "user_name";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER = "user_name";
    public static final String KEY_REGID = "user_regid";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_LOGGED = "is_logged";
    public static final String KEY_ISTOKEN = "is_token";

    public static final String KEY_FCMID = "fcmid";
    public static final String KEY_DEVICEID = "deviceid";

    public static final String KEY_REG = "is_register";
    public static final String KEY_SESSION = "session_id";
    public static final String KEY_SESSIONID = "session";

    public static final String KEY_PATIENT_ID = "patient_id";
    public static final String KEY_PRACTINER_ID = "practice_id";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String GCM_REG_ID = "gcm_reg_id";
    public static final String KEY_SPLASH = "splash";
    public static final String KEY_STATEID = "stateid";
    public static final String KEY_STATENAME= "statename";
    public static final String KEY_DISTRICTID = "districtid";
    public static final String KEY_DISTRICTNAME = "districtname";
    public static final String KEY_SECTIONID = "sectionid";
    public static final String KEY_SECTIONNAME = "sectionname";
    public static final String KEY_REALPATH = "realpath";
    private static final String APP_SHARED_PREFS = SharedPreferenceData.class
            .getSimpleName(); // Name of the file -.xml
    private static SharedPreferences _sharedPrefs;
    private static Editor _prefsEditor;

    @SuppressWarnings("static-access")
    public SharedPreferenceData(Context context) {
        this._sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS,
                Activity.MODE_PRIVATE);
        this._prefsEditor = _sharedPrefs.edit();
    }

    public static SharedPreferenceData getInstance() {
        return (SharedPreferenceData) _sharedPrefs;
    }

    public void delete() {
        // _prefsEditor.remove(KEY_PREFS_NAME);
        _prefsEditor.remove(KEY_USER_ID);
        _prefsEditor.commit();
    }

    public String getSplash() {
        return _sharedPrefs.getString(KEY_SPLASH, "");
    }

    public void setSplash(String sp) {
        _prefsEditor.putString(KEY_SPLASH, sp);
        _prefsEditor.commit();
    }

    public String getUser() {
        return _sharedPrefs.getString(KEY_USER, "");
    }

    public void setUser(String user) {
        _prefsEditor.putString(KEY_USER, user);
        _prefsEditor.commit();
    }

    public String getFCMID() {
        return _sharedPrefs.getString(KEY_FCMID, "");
    }

    public void setFCMID(String fcmid) {
        _prefsEditor.putString(KEY_FCMID, fcmid);
        _prefsEditor.commit();
    }

    public String getDEVICEID() {
        return _sharedPrefs.getString(KEY_DEVICEID, "");
    }

    public void setDEVICEID(String deviceid) {
        _prefsEditor.putString(KEY_DEVICEID, deviceid);
        _prefsEditor.commit();
    }

    public String getRegId() {
        return _sharedPrefs.getString(KEY_REGID, "");
    }

    public void setRegId(String user) {
        _prefsEditor.putString(KEY_REGID, user);
        _prefsEditor.commit();
    }

    public String getPassword() {
        return _sharedPrefs.getString(KEY_PASSWORD, "");
    }

    public void setPassword(String pass) {
        _prefsEditor.putString(KEY_PASSWORD, pass);
        _prefsEditor.commit();
    }

    public String getLastName() {
        return _sharedPrefs.getString(KEY_LAST_NAME, "");
    }

    public void setLastName(String id) {
        _prefsEditor.putString(KEY_LAST_NAME, id);
        _prefsEditor.commit();
    }

    public String getFirstName() {
        return _sharedPrefs.getString(KEY_FIRST_NAME, "");
    }

    public void setFirstName(String id) {
        _prefsEditor.putString(KEY_FIRST_NAME, id);
        _prefsEditor.commit();
    }

    public void setSessionId(String id) {
        _prefsEditor.putString(KEY_SESSIONID, id);
        _prefsEditor.commit();
    }

   /* public String getSession() {
        return _sharedPrefs.getString(KEY_SESSIONID, "");
    }

    public void sessionId(String id) {
        _prefsEditor.putString(KEY_SESSION, id);
        _prefsEditor.commit();
    }*/

    public String getSessionId() {
        return _sharedPrefs.getString(KEY_SESSIONID, "");
    }

    public void patientId(String id) {
        _prefsEditor.putString(KEY_PATIENT_ID, id);
        _prefsEditor.commit();
    }

    public String getPatientId() {
        return _sharedPrefs.getString(KEY_PATIENT_ID, "");
    }

    public String getUserId() {
        return _sharedPrefs.getString(KEY_USER_ID, "");
    }

    public void setUserId(String userId) {
        _prefsEditor.putString(KEY_USER_ID, userId);
        _prefsEditor.commit();
    }

    public boolean isLogged() {
        return _sharedPrefs.getBoolean(KEY_LOGGED, false);
    }

    public void setLogged(boolean key) {
        _prefsEditor.putBoolean(KEY_LOGGED, key);
        _prefsEditor.commit();
    }

    public boolean isRegistered() {
        return _sharedPrefs.getBoolean(KEY_REG, false);
    }

    public void setRegistered(boolean key) {
        _prefsEditor.putBoolean(KEY_REG, key);
        _prefsEditor.commit();
    }

    public String getStateid() {
        return _sharedPrefs.getString(KEY_STATEID, "");
    }

    public void setStateid(String stateid) {
        _prefsEditor.putString(KEY_STATEID, stateid);
        _prefsEditor.commit();
    }

    public String getStatename() {
        return _sharedPrefs.getString(KEY_STATENAME, "");
    }

    public void setStatename(String statename) {
        _prefsEditor.putString(KEY_STATENAME, statename);
        _prefsEditor.commit();
    }

    public String getDistrictname() {
        return _sharedPrefs.getString(KEY_DISTRICTNAME, "");
    }

    public void setDistrictname(String districtname) {
        _prefsEditor.putString(KEY_DISTRICTNAME, districtname);
        _prefsEditor.commit();
    }

    public String getDistrictid() {
        return _sharedPrefs.getString(KEY_DISTRICTID, "");
    }

    public void setDistrictid(String districtid) {
        _prefsEditor.putString(KEY_DISTRICTID, districtid);
        _prefsEditor.commit();
    }

    public String getSectionid() {
        return _sharedPrefs.getString(KEY_SECTIONID, "");
    }

    public void setSectionid(String sectionid) {
        _prefsEditor.putString(KEY_SECTIONID, sectionid);
        _prefsEditor.commit();
    }

    public String getSectionname() {
        return _sharedPrefs.getString(KEY_SECTIONNAME, "");
    }

    public void setSectionname(String sectionname) {
        _prefsEditor.putString(KEY_SECTIONNAME, sectionname);
        _prefsEditor.commit();
    }

    public String getRealpath() {
        return _sharedPrefs.getString(KEY_REALPATH, "");
    }

    public void setRealpath(String realpath) {
        _prefsEditor.putString(KEY_REALPATH, realpath);
        _prefsEditor.commit();
    }

}
