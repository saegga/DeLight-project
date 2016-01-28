package ru.delightfire.delight.util;

/**
 * Created by scaredChatsky on 28.01.2016.
 */
public class AuthHelper {

    public static boolean checkLogin(String login) {
        boolean result = false;

        if (login.matches("^[a-zA-Z][a-zA-Z0-9]*?([_][a-zA-Z0-9]+){0,2}$")
                && login.length() >= 4
                && login.length() <= 20)
            result = true;

        return result;
    }

    public static boolean checkPassword(String password) {
        boolean result = false;

        if (password.matches("^[a-zA-Z][a-zA-Z0-9]*?([_][a-zA-Z0-9]+){0,2}$")
                && password.length() >= 6
                && password.length() <= 30)
            result = true;

        return result;
    }

}
