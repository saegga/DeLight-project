package ru.delightfire.delight.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Событие в ленте событий - выступление (концерт)
 * @author scaredChatsky
 */
public class DelightShow extends DelightEvent {

    /**
     * Дата выступления
     */
    private Date date;

    /**
     * Заказчик
     */
    private DelightClient client;

    /**
     * Номера в выступлении
     */
    private List<DelightPerformance> performances;

    public DelightShow(Date d, String agenda, String login, String name){
        super(agenda, login, name);
        date = d;
    }

    public DelightShow(Date d, String login, String name) {
        super(login, name);
        date = d;
    }
}
