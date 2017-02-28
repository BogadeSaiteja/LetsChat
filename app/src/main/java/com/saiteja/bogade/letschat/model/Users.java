package com.saiteja.bogade.letschat.model;

/**
 * Created by saite_000 on 2/19/2017.
 */
public class Users {
    private String emailId;
    private String lastMessage;
    private int notifCount;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getNotifCount() {
        return notifCount;
    }

    public void setNotifCount(int notifCount) {
        this.notifCount = notifCount;
    }
}
