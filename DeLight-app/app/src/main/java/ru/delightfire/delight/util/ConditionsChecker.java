package ru.delightfire.delight.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scaredChatsky on 09.01.2016.
 */
public class ConditionsChecker {

    private List<Boolean> conditions = new ArrayList<>();

    public ConditionsChecker(int count) {
        for (int i = 0; i < count; i++)
            this.addCondition();
    }

    public synchronized boolean isComplete() {
        for (int i = 0; i < conditions.size(); i++) {
            if (conditions.get(i))
                continue;
            else return false;
        }
        return true;
    }

    public void addCondition() {
        conditions.add(false);
    }

    public synchronized void wasComplete() {
        for (int i = 0; i < conditions.size(); i++) {
            if (!conditions.get(i)) {
                conditions.set(i, true);
                return;
            }
        }
    }

}
