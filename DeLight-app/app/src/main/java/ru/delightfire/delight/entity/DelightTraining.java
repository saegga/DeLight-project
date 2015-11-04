package ru.delightfire.delight.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scaredChatsky on 24.10.2015.
 */
public class DelightTraining extends DelightEvent{

    /**
     * Присутствующие пользователи
     */
    private List<DelightUser> usersOnTraining;

    /**
     * Время
     */
    String time;

    /**
     * День недели
     */
    String dayOfWeek;


    public DelightTraining(String agenda, String login, String name){
        super(agenda, login, name);
        usersOnTraining = new ArrayList<DelightUser>();
    }

    public DelightTraining(String login, String name){
        super(login, name);
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



}
