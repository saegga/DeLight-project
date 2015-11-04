package ru.delightfire.delight.entity;

/**
 * Created by sergei on 02.11.2015.
 */
public class Message {

    private String message;
    private boolean yourMsg;
    private String name;
    public Message(String message) {
        this.message = message;
    }
    public Message(String message, String name){
        this.name = name;
        this.message = message;
    }
    public Message(String message, String name, boolean yourMsg){
        this.name = name;
        this.yourMsg = yourMsg;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isYourMsg() {
        return yourMsg;
    }

    public void setYourMsg(boolean yourMsg) {
        this.yourMsg = yourMsg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
