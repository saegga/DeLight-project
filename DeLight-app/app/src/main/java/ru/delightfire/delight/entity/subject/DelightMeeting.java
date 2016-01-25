package ru.delightfire.delight.entity.subject;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Класс реализующий поля и методы для работы со встречами/произвольными событиями
 *
 * @author scaredChatsky
 * @see DelightEvent
 */
public class DelightMeeting extends DelightEventExtended {

    public DelightMeeting(int placeId, int month, int day, String startTime, String endTime, String name) {
        super(placeId, month, day, startTime, endTime);
        this.name = name;
    }
}
