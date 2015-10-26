package ru.delightfire.delight.entity;

import java.util.Date;

/**
 * Created by scaredChatsky on 24.10.2015.
 */
public class DelightPerformance extends DelightEvent {

    private Date date;
    private DelightClient client;

    public DelightPerformance(Date d, String agenda, String login, String name){
        super(agenda, login, name);
        date = d;
    }

    public DelightPerformance(Date d, String login, String name) {
        super(login, name);
        date = d;
    }
}
