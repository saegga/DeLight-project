package ru.delightfire.delight.entity;

/**
 * Created by sergei on 02.11.2015.
 * Сообщение в чате
 *
 * @author sergei
 *         TODO: JavaDoc!
 */
public class ChatMessage {


    private String message;

    //TODO: Для проверки в чате твое сообщение или нет?
    //А если у всех сообщений будет true, значит все твои? :)
    //Целесообразно, наверное, хранить логин отправившего и сравнивать его со своим в методе isYoutMsg?
    private boolean yourMsg;
    private String name;

    public ChatMessage(String message) {
        this.message = message;
    }

    public ChatMessage(String message, String name) {
        this.name = name;
        this.message = message;
    }

    public ChatMessage(String message, String name, boolean yourMsg) {
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

    //TODO:
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
