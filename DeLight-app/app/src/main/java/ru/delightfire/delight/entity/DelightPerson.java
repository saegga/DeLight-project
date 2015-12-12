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
     * Имя
     */
    protected String firstName;

    /**
     * Фамилия
     */
    protected String lastName;

    public DelightPerson(String firstName) {
        this.firstName = firstName;
    }

    public DelightPerson(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public DelightPerson(){

    }
}
