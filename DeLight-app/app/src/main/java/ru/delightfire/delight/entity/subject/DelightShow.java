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
    
}
