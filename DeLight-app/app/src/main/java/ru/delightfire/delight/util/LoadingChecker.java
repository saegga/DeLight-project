package ru.delightfire.delight.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scaredChatsky on 09.01.2016.
 */
public class LoadingChecker {

    private List<Boolean> conditions = new ArrayList<>();

    public LoadingChecker(int count) {
        for (int i = 0; i < count; i++)
            this.addCondition();
    }

    public synchronized boolean isLoaded() {
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

    public void wasComplete() {
        for (int i = 0; i < conditions.size(); i++) {
            if (!conditions.get(i)) {
                conditions.set(i, true);
                return;
            }
        }
    }

}
