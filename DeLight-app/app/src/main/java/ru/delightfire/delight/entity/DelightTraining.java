package ru.delightfire.delight.entity;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scaredChatsky on 24.10.2015.
 */
public class DelightTraining extends DelightEvent{
    /*
    * id тренировки
    * */
    @Expose (deserialize = false)
    private int trainingId;
    /**
     * логин создавшего событие
     */

    private String ownerLogin;
    /**
     * Присутствующие пользователи
     */
    private List<DelightUser> usersOnTraining;

    /**
     * Время
     */
    private String time;

    /**
     * День недели
     */
    private String dayOfWeek;
    /*
    * место события
    * */
    private String place;

    /*
     описание события
    * */
    private String agenda;


    public DelightTraining(String agenda,String name,String dayOfWeek, String time, String place){
        super(agenda, name, dayOfWeek, time);
        usersOnTraining = new ArrayList<>();
    }

    public DelightTraining(String login, String name, List<DelightUser> usersOnTraining, String time, String dayOfWeek) {
        super(login, name);
        this.usersOnTraining = usersOnTraining;
        this.time = time;
        this.dayOfWeek = dayOfWeek;
    }

    public DelightTraining(String login, String name){
        super(login, name);
    }

    public DelightTraining() {
       //super();
    }

    public DelightTraining(String agenda, String name, String dateEvent, String timeEvent, String place, String ownerLogin, List<DelightUser> usersOnTraining) {
        super(agenda, name, dateEvent, timeEvent);
        usersOnTraining = new ArrayList<>();
        this.ownerLogin = ownerLogin;
        this.usersOnTraining = usersOnTraining;
        this.time = timeEvent;
        this.dayOfWeek = dateEvent;
        this.place = place;
        this.agenda = agenda;
    }

    /**
     * Добавление нового пользователя
     * @param user
     */
    public void addUser(DelightUser user){
        usersOnTraining.add(user);
    }

    /**
     * Удаление пользователя
     * @param user
     */
    public void deleteUser(DelightUser user){
        usersOnTraining.remove(user);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
