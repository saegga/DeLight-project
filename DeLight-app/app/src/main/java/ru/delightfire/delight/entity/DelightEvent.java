package ru.delightfire.delight.entity;

/**
 * Created by scaredChatsky on 24.10.2015.
 * Абстрактный класс, описывающий общие поля и методы, для работы с событиями
 *
 * @author scaredChatsky
 * @see DelightTraining
 * @see DelightShow
 * @see DelightMeeting
 */
public abstract class DelightEvent {
    /**
     * Описание события, повестка дня, цели и задачи события
     */
    protected String agenda;
    /**
     * Логин пользователя, создавшего событие
     */
    protected String ownerLogin;
    /**
     * Название события
     */
    protected String name;
    /*
    *  Дата события
    * */
    protected String dateEvent;
    /*
    * время события для тренировки
    * */
    protected String timeEvent;
    /*
    *   место события
    * */
    protected String place;

    // вызов констуктора для события в список
    protected DelightEvent(String agenda, String name, String dateEvent, String timeEvent) {
        this.name = name;
        this.agenda = agenda;
        this.dateEvent = dateEvent;
        this.timeEvent = timeEvent;

    }

    /**
     * Инициализация полей {@link DelightEvent#agenda}, {@link DelightEvent#ownerLogin},
     * {@link DelightEvent#name}
     *
     * @param agenda    Описание события
     * @param dateEvent полная дата события
     * @param name      Имя события
     */
    protected DelightEvent(String agenda, String name, String dateEvent) {
        this.name = name;
        this.agenda = agenda;
        this.dateEvent = dateEvent;

    }

    /**
     * Инициализация полей {@link DelightEvent#ownerLogin},
     * {@link DelightEvent#name}
     *
     * @param login Логин пользователя, создавшего событие
     * @param name  Имя события
     */
    protected DelightEvent(String login, String name) {
        this.name = name;
        this.ownerLogin = login;
    }

    public DelightEvent() {
    }

    /**
     * Позволяет изменить имя события
     *
     * @param name Имя события
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Изменение описания события
     *
     * @param agenda Описание события
     */
    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    /**
     * Возвращает имя события
     *
     * @return Имя события
     */
    public String getName() {
        return name;
    }

    public String getAgenda() {
        return agenda;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public String getTimeEvent() {
        return timeEvent;
    }
}
