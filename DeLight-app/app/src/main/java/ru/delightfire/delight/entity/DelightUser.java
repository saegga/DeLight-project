package ru.delightfire.delight.entity;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Участник команды
 * @author scaredChastky
 */
public class DelightUser extends DelightPerson{

    /**
     * Может ли открывать зал
     */
    private boolean key;

    /**
     * Роль участника в группе
     * @see DeliteRole
     */
    private DeliteRole role;

    private boolean status;

    /**
     * Логин пользователя
     */
    private String login;

    /**
     * Пароль
     */
    private String password;
    /*
        ключи для сохранения пользователя
    * */
    public static final String PREF_AUTH = "pref_auth";
    public static final String PREF_FIRST_NAME = "pref_first_name";
    public static final String PREF_LAST_NAME = "pref_last_name";
    public static final String PREF_LOGIN = "pref_login";
    public static final String PREF_PASSWORD = "pref_password";

    public DelightUser(String login, String password, String firstName, String lastName){
        super(firstName, lastName);
        this.login = login;
        this.password = password;
    }

    public DelightUser(String login, String password){
        this.login = login;
        this.password = password;
    }

    public void saveUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_AUTH, Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(PREF_LOGIN, login);
                                    editor.putString(PREF_PASSWORD, password);
                                    editor.commit();
        // добавить сохранение остальных полей при добавлении инфы
    }
    public static void deleteUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_AUTH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
