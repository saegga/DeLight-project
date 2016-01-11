package ru.delightfire.delight.entity;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Событие в ленте событий - выступление (концерт)
 * @author scaredChatsky
 */
public class DelightShow extends DelightEvent {

    @Expose(deserialize = false)
    private int id;
    /**
     * Дата выступления
     */
    private  String date;

    /**
     * Заказчик
     */
    private DelightClient client;

    /**
     * Номера в выступлении
     */
    private List<DelightPerformance> performances;
    /*
       название шоу
   * */
    private String name;
    /*
    * описание шоу
    * */
    private String description;
    /*
    *  место проведения
    * */
    private String place;

    /*
    *  деньги
    * */
    private String money;

    @Expose(deserialize = false)
    private int customId;


//    public DelightShow(Date d, String agenda, String login, String name){
//        super(agenda, login, name);
//        date = d;
//    }

    public DelightShow(String d, String description, String name, String money, String place) {
        super(description, name, d);
        this.money = money;
        this.place = place;
        // добавить клиента
    }
}
