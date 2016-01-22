package ru.delightfire.delight.entity.subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scaredChatsky on 24.10.2015.
 */
public class DelightTraining extends DelightEventExtended {

    /**
     * Присутствующие пользователи
     */
    private List<DelightUser> usersOnTraining = new ArrayList<>();

    /**
     * Добавление нового пользователя
     *
     * @param user
     */
    public void addUser(DelightUser user) {
        usersOnTraining.add(user);
    }

    /**
     * Удаление пользователя
     *
     * @param user
     */
    public void removeUser(DelightUser user) {
        usersOnTraining.remove(user);
    }

}
