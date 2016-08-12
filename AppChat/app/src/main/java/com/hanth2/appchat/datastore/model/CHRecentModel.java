package com.hanth2.appchat.datastore.model;

import com.hanth2.appchat.datastore.entities.CHRecentEntity;

import java.util.ArrayList;

/**
 * Created by HanTH2 on 8/11/2016.
 */
public class CHRecentModel {

    private String id = "";
    private String sender = "";
    private String photo = "";
    private String lastMessage = "";
    private long lastMessageDateSent;
    private String roomJid = "";
    private String userLogin = "";
    private int readMessage;
    private String subject = "";
    private ArrayList<String> occupants;
    private int type;
    private String conversationId = "";

    public CHRecentModel() {
    }

    public CHRecentModel(Object object) {
        CHRecentEntity chRecentEntity = (CHRecentEntity) object;
        this.setId(chRecentEntity.getId());
        this.setUserLogin(chRecentEntity.getUserLogin());
        this.setSubject(chRecentEntity.getSubject());
        this.setConversationId(chRecentEntity.getConversationId());
        this.setSender(chRecentEntity.getSender());
        this.setPhoto(chRecentEntity.getPhoto());
        this.setLastMessage(chRecentEntity.getLastMessage());
        this.setLastMessageDateSent(chRecentEntity.getLastMessageDateSent());
        this.setReadMessage(chRecentEntity.getReadMessage());
        this.setType(chRecentEntity.getType());
        this.setConversationId(chRecentEntity.getConversationId());
        if (null != chRecentEntity.getOccupants()) {
            String[] opt = chRecentEntity.getOccupants().split(",");
            for (int i = 0; i < opt.length - 1; i++) {
                this.addOccupants(opt[i]);
            }

        }
    }


    public String getId() {
        if (null == id)
            id = "";
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastMessage() {
        if (null == lastMessage)
            lastMessage = "";
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public long getLastMessageDateSent() {
        return lastMessageDateSent;
    }

    public void setLastMessageDateSent(long lastMessageDateSent) {
        this.lastMessageDateSent = lastMessageDateSent;
    }

    public String getPhoto() {
        if (null == photo)
            photo = "";
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRoomJid() {
        if (null == roomJid)
            roomJid = "";
        return roomJid;
    }

    public void setRoomJid(String roomJid) {
        this.roomJid = roomJid;
    }

    public String getSender() {
        if (null == sender)
            sender = "";
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getUserLogin() {
        if (null == userLogin)
            userLogin = "";
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getReadMessage() {
        return readMessage;
    }

    public void setReadMessage(int readMessage) {
        this.readMessage = readMessage;
    }

    public String getSubject() {
        if (null == subject)
            subject = "";
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ArrayList<String> getOccupants() {
        return occupants;
    }

    public void setOccupants(ArrayList<String> occupants) {
        this.occupants = occupants;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getConversationId() {
        if (null == conversationId)
            conversationId = "";
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public void addOccupants(String opt){
        if (null == occupants){
            occupants = new ArrayList<>();
        }
        occupants.add(opt);
    }
}
