package ru.delightfire.delight.entity;

import java.util.List;

/**
 * Created by scaredChatsky on 04.11.2015.
 * Отдельный номер для выступления
 */
public class DelightPerformance {
    /**
     * Название номера
     */
    private String name;

    /**
     * Описание номера
     */
    private String description;

    /**
     * Пользователи, участвующие в номере
     */
    private List<DelightUser> usersInPerformance;
}
