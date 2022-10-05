package com.example.novelflex.LocalCache;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.novelflex.Constants.Constant;

public class SharedPrefManager {

    public static SharedPreferences getSharedPref(Context mContext) {
        SharedPreferences pref = mContext.getSharedPreferences(Constant.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        return pref;
    }

    public static void setLanguageSelectionPref(Context mContext, String key, int value) {
        if (key != null) {
            SharedPreferences.Editor edit = getSharedPref(mContext).edit();
            edit.putInt(key, value);
            edit.apply();
        }
    }

    public static int getLanguageSelectionPref(Context mContext, String key) {
        SharedPreferences pref = getSharedPref(mContext);
        int val = 0;
        try {
            if (pref.contains(key)) val = pref.getInt(key, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }

    public static void setPrefVal(Context mContext, String key, String value) {
        if (key != null) {
            SharedPreferences.Editor edit = getSharedPref(mContext).edit();
            edit.putString(key, value);
            edit.apply();
        }
    }

    public static void setIntPrefVal(Context mContext, String key, int value) {
        if (key != null) {
            SharedPreferences.Editor edit = getSharedPref(mContext).edit();
            edit.putInt(key, value);
            edit.apply();
        }
    }

    public static void setLongPrefVal(Context mContext, String key, Long value) {
        if (key != null) {
            SharedPreferences.Editor edit = getSharedPref(mContext).edit();
            edit.putLong(key, value);
            edit.apply();
        }
    }

    public static void setBooleanPrefVal(Context mContext, String key, boolean value) {
        if (key != null) {
            SharedPreferences.Editor edit = getSharedPref(mContext).edit();
            edit.putBoolean(key, value);
            edit.apply();
        }
    }


    public static String getPrefVal(Context mContext, String key) {
        SharedPreferences pref = getSharedPref(mContext);
        String val = "";
        try {
            if (pref.contains(key))
                val = pref.getString(key, "");
            else
                val = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }

    public static int getIntPrefVal(Context mContext, String key) {
        SharedPreferences pref = getSharedPref(mContext);
        int val = 0;
        try {
            if (pref.contains(key)) val = pref.getInt(key, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }

    public static Long getLongPrefVal(Context mContext, String key) {
        SharedPreferences pref = getSharedPref(mContext);
        Long val = null;
        try {
            if (pref.contains(key)) val = pref.getLong(key, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }

    public static boolean getBooleanPrefVal(Context mContext, String key) {
        SharedPreferences pref = getSharedPref(mContext);
        boolean val = false;
        try {
            if (pref.contains(key)) val = pref.getBoolean(key, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }


    public static boolean containkey(Context mContext, String key) {
        SharedPreferences pref = getSharedPref(mContext);
        return pref.contains(key);
    }

    public static void saveTestScreens(Context mContext, String key,
                                       String value) {
        SharedPreferences.Editor edit = getSharedPref(mContext).edit();
        edit.putString(key, value);
        edit.apply();
    }

    public static String getTestScreens(Context mContext, String key) {
        SharedPreferences pref = getSharedPref(mContext);
        String val = "";
        if (pref.contains(key))
            val = pref.getString(key, "");
        else
            val = "";
        return val;
    }

    //UserData Storage During Login

    public static void setUserName(Context context,String key,String value){
        SharedPreferences.Editor editor=getSharedPref(context).edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static String getUserName(Context context,String key){
        return getSharedPref(context).getString(key,"");
    }

    public static void setUserEmail(Context context,String key,String value){
        SharedPreferences.Editor editor=getSharedPref(context).edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static String getUserEmail(Context context,String key){
        return getSharedPref(context).getString(key,"");
    }

    public static void setUserID(Context context,String key,String value){
        SharedPreferences.Editor editor=getSharedPref(context).edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static String getUserID(Context context,String key){
        return getSharedPref(context).getString(key,"");
    }

    public static void setUserPhone(Context context,String key,String value){
        SharedPreferences.Editor editor=getSharedPref(context).edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static String getUserPhone(Context context,String key){
        return getSharedPref(context).getString(key,"");
    }

    public static void LogOut(Context context,String key){
        SharedPreferences.Editor editor=getSharedPref(context).edit();
        editor.remove(key);
        editor.apply();
    }

}
