package ru.delightfire.delight.entity;

import java.util.Date;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Класс реализующий поля и методы для работы со встречами/произвольными событиями
 * @author scaredChatsky
 * @see DelightEvent
 */
public class DelightMeeting extends DelightEvent {

    /**
     * Дата события
     */
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
