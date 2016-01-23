package ru.delightfire.delight.entity.subject;

/**
 * Created by scaredChatsky on 22.01.2016.
 */
public abstract class DelightEventExtended extends DelightEvent {

    /**
     * Описание события, повестка дня, цели и задачи события
     */
    protected String agenda;

    protected int ownerId;

    public String getAgenda() {
        return agenda;
    }

    public int getOwnerId() {
        return ownerId;
    }
}
