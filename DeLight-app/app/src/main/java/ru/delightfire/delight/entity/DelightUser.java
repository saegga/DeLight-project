package ru.delightfire.delight.entity;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Участник команды
 * @author scaredChastky
 */
public class DelightUser extends DelightPerson{

    /**
     * Может ли открывать зал
     */
    private boolean key;

    /**
     * Роль участника в группе
     * @see DeliteRole
     */
     private DeliteRole role;

}
