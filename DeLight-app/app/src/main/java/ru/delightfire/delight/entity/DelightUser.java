package ru.delightfire.delight.entity;

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

    public DelightUser(String login, String password, String firstName, String lastName){
        super(firstName);
        this.login = login;
        this.password = password;
    }

    public DelightUser(String login, String password){
        this.login = login;
        this.password = password;
    }

}
