package ru.delightfire.delight.util;

import android.content.Context;
import android.content.SharedPreferences;

import ru.delightfire.delight.entity.subject.DelightUser;

/**
 * Created by sergei on 15.12.2015.
 */
public class UserAccount {

    /**
     * ключи для сохранения пользователя
     */
    public static final String PREF_AUTH = "pref_auth";
    public static final String PREF_LOGIN = "pref_login";
    public static final String PREF_PASSWORD = "pref_password";

    private static DelightUser user;

    private static class Holder {
        private static final UserAccount instance = new UserAccount();
    }

    public static UserAccount getInstance() {
        return Holder.instance;
    }

    public boolean saveUser(Context context, DelightUser user) {
        if (getUser(context) == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_AUTH, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(PREF_LOGIN, user.getLogin());
            editor.putString(PREF_PASSWORD, user.getPassword());
            editor.apply();
            return true;
        } else {
            return false;
        }
    }

    public DelightUser getUser(Context context) {
        if (user == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_AUTH, Context.MODE_PRIVATE);
            String login = sharedPreferences.getString(PREF_LOGIN, null);
            String pass = sharedPreferences.getString(PREF_PASSWORD, null);
            if (login != null && pass != null)
                user = new DelightUser(login, pass);
        }
        return user;
    }

    public boolean deleteUser(Context context) {
        if (getUser(context) != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_AUTH, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            user = null;
            return true;
        } else {
            return false;
        }
    }
}
