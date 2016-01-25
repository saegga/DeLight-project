package ru.delightfire.delight.entity.subject;

import java.util.List;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Событие в ленте событий - выступление (концерт)
 *
 * @author scaredChatsky
 */
public class DelightShow extends DelightEventExtended {

    /**
     * Заказчик
     */
    private DelightClient client;

    /**
     * Номера в выступлении
     */
    private List<DelightPerformance> performances;

    public DelightShow(int placeId, int month, int day, String startTime, String endTime) {
        super(placeId, month, day, startTime, endTime);
    }
    public DelightShow(int placeId, int month, int day, String startTime, String endTime, String extra){
        super(placeId, month, day, startTime, endTime, extra);
    }
}
