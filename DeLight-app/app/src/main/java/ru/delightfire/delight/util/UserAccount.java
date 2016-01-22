package ru.delightfire.delight.util;

import android.content.Context;
import android.content.SharedPreferences;

import ru.delightfire.delight.entity.subject.DelightUser;

/**
 * Created by sergei on 15.12.2015.
 */
public class UserAccount {

    private static Context appContext;

    private UserAccount(Context context) {
        appContext = context;
    }

    private static class Holder {
        private static final UserAccount instance = new UserAccount(appContext);
    }

    public static UserAccount getInstance() {
        return Holder.instance;
    }

    public void saveUser(Context context, DelightUser user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DelightUser.PREF_AUTH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DelightUser.PREF_LOGIN, user.getLogin());
        editor.putString(DelightUser.PREF_PASSWORD, user.getPassword());
        editor.apply();
    }

    public DelightUser getUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DelightUser.PREF_AUTH, Context.MODE_PRIVATE);
        String login = sharedPreferences.getString(DelightUser.PREF_LOGIN, null);
        String pass = sharedPreferences.getString(DelightUser.PREF_PASSWORD, null);
        return new DelightUser(login, pass);
    }

    public void deleteUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DelightUser.PREF_AUTH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
