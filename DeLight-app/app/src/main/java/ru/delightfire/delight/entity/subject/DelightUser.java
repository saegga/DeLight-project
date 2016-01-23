package ru.delightfire.delight.entity.subject;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Участник команды
 *
 * @author scaredChastky
 */
public class DelightUser extends DelightPerson {

    /**
     * Может ли открывать зал
     */
    private boolean key;

    /**
     * Роль участника в группе
     *
     * @see DeliteRole
     */
    private DeliteRole role;

    /**
     * Онлайн - оффлайн
     */
    private boolean status;

    /**
     * Логин пользователя
     */
    private String login;

    /**
     * Пароль
     */
    private String password;

    /**
     * ключи для сохранения пользователя
     */
    public static final String PREF_AUTH = "pref_auth";
    public static final String PREF_LOGIN = "pref_login";
    public static final String PREF_PASSWORD = "pref_password";

    public DelightUser(String login, String password, String firstName, String lastName) {
        super(firstName, lastName);
        this.login = login;
        this.password = password;
    }

    public DelightUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
