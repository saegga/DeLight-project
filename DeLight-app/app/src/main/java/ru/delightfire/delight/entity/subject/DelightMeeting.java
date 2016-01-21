package ru.delightfire.delight.entity.subject;

import com.google.gson.annotations.Expose;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Класс реализующий поля и методы для работы со встречами/произвольными событиями
 *
 * @author scaredChatsky
 * @see DelightEvent
 */
public class DelightMeeting extends DelightEvent {

    @Expose(deserialize = false)
    private int id;
    /**
     * Дата события
     */
    private String date;

    /*
    * краткое название встречи
    * */
    private String name;
    /*
   * повестка дня что нужно сделать
   * */
    private String agenda;

    /*
   *   место встречи
   * */
    private String place;


    public DelightMeeting(String d, String agenda, String name, String place) {
        super(agenda, name, d);
        this.place = place;
        // todo поля
    }
}
