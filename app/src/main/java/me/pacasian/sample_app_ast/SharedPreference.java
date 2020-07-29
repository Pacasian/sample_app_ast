package me.pacasian.sample_app_ast;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    private static SharedPreference store;
    private SharedPreferences SP;
    private static String filename="Keys";

    private SharedPreference(Context context) {
        SP = context.getApplicationContext().getSharedPreferences(filename,0);
    }

    public static SharedPreference getInstance(Context context) {
        if (store == null) {
            store = new SharedPreference(context);
        }
        return store;
    }


    public void put(String key, String value) {
        SharedPreferences.Editor editor = SP.edit();
        editor.putString(key, value);
        editor.commit(); // Stop everything and do an immediate save!
        // editor.apply();//Keep going and save when you are not busy - Available only in APIs 9 and above.  This is the preferred way of saving.
    }

    public String get(String key) {
        return SP.getString(key, null);

    }

    public int getInt(String key) {
        return SP.getInt(key, 0);
    }

    public void putInt(String key, int num) {
        SharedPreferences.Editor editor = SP.edit();

        editor.putInt(key, num);
        editor.commit();
    }
}