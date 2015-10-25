package ru.delightfire.delight.entity;

import java.util.Date;

/**
 * Created by dev on 24.10.2015.
 */
public class DelightMeeting extends DelightEvent {

    private Date date;

    public DelightMeeting(Date d, String agenda, String login, String name){
        super(agenda, login, name);
        date = d;
    }

    public DelightMeeting(Date d, String login, String name){
        super(login, name);
        date = d;
    }

}
