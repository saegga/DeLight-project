package ru.delightfire.delight.entity.subject;

/**
 * Created by scaredChatsky on 22.01.2016.
 */
public abstract class DelightEventExtended extends DelightEvent {

    /**
     * Описание события, повестка дня, цели и задачи события
     */
    protected String agenda;

    protected String name;

    public String getAgenda() {
        return agenda;
    }

    public DelightEventExtended(int placeId, int month, int day, String startTime, String endTime){
        super(placeId, month, day, startTime, endTime);
    }

    public DelightEventExtended(int month, int day, String startTime, String endTime) {
        super(month, day, startTime, endTime);
    }

    public String getName() {
        return name;
    }
}
