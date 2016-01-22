package ru.delightfire.delight.entity.subject;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Класс, описывающий общие поля и методы, для работы с событиями
 *
 * @author scaredChatsky
 * @see DelightTraining
 * @see DelightShow
 * @see DelightMeeting
 */
public class DelightEvent {

    protected int eventId;

    /**
    *  Дата события
    */
    protected String date;

    /**
    * Время начала
    */
    protected String startTime;

    protected String endTime;

    /**
    * Id места проведения
    */
    protected int placeId;

    public DelightEvent(int eventId, int placeId, String date, String startTime, String endTime){
        this.eventId = eventId;
        this.placeId = placeId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DelightEvent() {

    }

    public int getEventId() {
        return eventId;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getPlaceId() {
        return placeId;
    }
}
