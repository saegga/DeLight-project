package ru.delightfire.delight.entity;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Клиент, заказавший выступление (DelightShow)
 */
public class DelightClient extends DelightPerson {

    DelightClient(String firstName, String phone) {
        super(firstName);
        this.phone = phone;
    }

}
