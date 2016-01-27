package ru.delightfire.delight.entity.subject;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Участник команды
 *
 * @author scaredChastky
 */
public class DelightUser extends DelightPerson {

    private int userId;
    /**
     * Может ли открывать зал
     */
    private boolean key;

    /**
     * Роль участника в группе
     *
     * @see DelightRole
     */
    private DelightRole role;

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

    public DelightUser(String login, String password, String firstName, String lastName) {
        super(firstName, lastName);
        this.login = login;
        this.password = password;
    }

    public DelightUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public DelightUser(int userId, String login, String password) {
        this.userId = userId;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return userId;
    }
}
