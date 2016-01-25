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

    public DelightEventExtended(int placeId, int month, int day, String startTime, String endTime){
        super(placeId, month, day, startTime, endTime);
    }
    public DelightEventExtended(int placeId, int month, int day, String startTime, String endTime,String extra){
        super(placeId, month, day, startTime, endTime, extra);
    }
}
