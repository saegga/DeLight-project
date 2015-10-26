package ru.delightfire.delight.entity;

/**
 * Created by scaredChatsky on 24.10.2015.
 */
public abstract class DelightEvent {

    protected String agenda;
    protected String ownerLogin;
    protected String name;

    protected DelightEvent(String agenda, String login, String name){
        this.name = name;
        this.agenda = agenda;
        this.ownerLogin = login;
    }

    protected DelightEvent(String login, String name){
        this.name = name;
        this.ownerLogin = login;
    }

    protected void setName(String name){
        this.name = name;
    }

    protected void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getName() {
        return name;
    }
}
