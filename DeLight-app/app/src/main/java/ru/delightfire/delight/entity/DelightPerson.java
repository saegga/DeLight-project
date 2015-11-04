package ru.delightfire.delight.entity;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Абстрактный класс, описывающий общие поля и методы для работы с людьми, пользователями
 * @author scaredChatsky
 */
public abstract class DelightPerson {

    /**
     * Номер телефона
     */
    protected String phone;

    /**
     * Регистрационный ключ
     */
    protected String regKey;

    /**
     * Логин дял входа
     */
    private String login;

    /**
     * Имя
     */
    private String firstName;

    /**
     * Фамилия
     */
    private String lastName;

    /**
     * Пароль
     */
    private String password;

    /**
     * Онлайн/оффлайн
     */
    private boolean status;

    public DelightPerson(String firstName) {
        this.firstName = firstName;
    }

    public DelightPerson() {
    }
}
