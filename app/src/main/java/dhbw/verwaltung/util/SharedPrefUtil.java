package dhbw.verwaltung.util;

import android.content.SharedPreferences;

public class SharedPrefUtil {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences loginPreferences;

    public static String getString(String key, String defaultValue) {
        return sharedPreferences == null ? defaultValue : sharedPreferences.getString(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static void setSharedPreferences(SharedPreferences sharedPreferences) {
        SharedPrefUtil.sharedPreferences = sharedPreferences;
    }

    public static SharedPreferences getLoginPreferences() {
        return loginPreferences;
    }

    public static void setLoginPreferences(SharedPreferences loginPreferences) {
        SharedPrefUtil.loginPreferences = loginPreferences;
    }

    public static SharedPreferences.Editor getSharedPrefEditor() {
        return sharedPreferences.edit();
    }

    public static SharedPreferences.Editor getLoginPrefEditor() {
        return loginPreferences.edit();
    }
}
