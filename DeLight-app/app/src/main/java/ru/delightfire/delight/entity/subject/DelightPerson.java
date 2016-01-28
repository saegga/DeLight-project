package ru.delightfire.delight.entity.subject;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Абстрактный класс, описывающий общие поля и методы для работы с людьми, пользователями
 *
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

    public DelightPerson() {

    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
