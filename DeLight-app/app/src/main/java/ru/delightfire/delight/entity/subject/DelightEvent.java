package ru.delightfire.delight.entity.subject;

import android.support.annotation.NonNull;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Класс, описывающий общие поля и методы, для работы с событиями
 *
 * @author scaredChatsky
 * @see DelightTraining
 * @see DelightShow
 * @see DelightMeeting
 */
public class DelightEvent implements Comparable{

    protected int eventId;

    /**
     * Дата события
     */
    protected int month;

    protected int day;

    /**
     * Время начала
     */
    protected String startTime;

    protected String endTime;

    /**
     * Id места проведения
     */
    protected int placeId;

    private String extra;

    public DelightEvent(int eventId, int placeId, int month, int day, String startTime, String endTime, String placeName) {
        this.eventId = eventId;
        this.placeId = placeId;
        this.month = month;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.extra = placeName;
    }

    public DelightEvent() {

    }

    public int getEventId() {
        return eventId;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
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

    public static String getMonthName(int month) {
        String monthName = null;

        switch (month) {
            case 1:
                monthName = "Января";
                break;
            case 2:
                monthName = "Февраля";
                break;
            case 3:
                monthName = "Марта";
                break;
            case 4:
                monthName = "Апреля";
                break;
            case 5:
                monthName = "Мая";
                break;
            case 6:
                monthName = "Июня";
                break;
            case 7:
                monthName = "Июля";
                break;
            case 8:
                monthName = "Августа";
                break;
            case 9:
                monthName = "Сентября";
                break;
            case 10:
                monthName = "Октября";
                break;
            case 11:
                monthName = "Ноября";
                break;
            case 12:
                monthName = "Декабря";
                break;
        }

        return monthName;
    }

    @Override
    public int compareTo(@NonNull Object another) {
        DelightEvent event = (DelightEvent) another;

        int result = this.month - event.getMonth();
        if (result != 0){
            return result;
        }

        result = this.day - event.getDay();
        if (result != 0){
            return result;
        }

        return 0;
    }

    public String getExtra() {
        return extra;
    }
}
