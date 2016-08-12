package com.hanth2.appchat.datastore.entities;

import com.hanth2.appchat.datastore.model.CHRecentModel;

/**
 * Created by huylx on 10/06/2016.
 */
public class /**
 * Created by HanTH2 on 8/11/2016.
 */CHRecentEntity {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static final String TABLE_NAME = "recent_tab";
    public static final String _ID = "_id";
    public static final String _SENDER = "_sender";
    public static final String _PHOTO = "_photo";
    public static final String _USERLOGIN = "_userLogin";
    public static final String _READMESSAGE = "_readMessage";
    public static final String _LASTMESSAGE = "_lastMessage";
    public static final String _LASTMESSAGEDATESENT = "_lastMessageDateSent";
    public static final String _ROOMJID = "_roomJid";
    public static final String _CONVERATIONID = "_conversationId";
    public static final String _TYPE = "_type";
    public static final String _SUBJECT = "_subject";
    public static final String _OCCUPANTS = "_occupants";


    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    _SENDER + TEXT_TYPE + COMMA_SEP +
                    _PHOTO + TEXT_TYPE + COMMA_SEP +
                    _USERLOGIN + TEXT_TYPE + COMMA_SEP +
                    _READMESSAGE + TEXT_TYPE + COMMA_SEP +
                    _LASTMESSAGE + TEXT_TYPE + COMMA_SEP +
                    _LASTMESSAGEDATESENT + TEXT_TYPE + COMMA_SEP +
                    _ROOMJID + TEXT_TYPE + COMMA_SEP +
                    _TYPE + TEXT_TYPE + COMMA_SEP +
                    _CONVERATIONID + TEXT_TYPE + COMMA_SEP +
                    _SUBJECT + TEXT_TYPE + COMMA_SEP +
                    _OCCUPANTS + TEXT_TYPE +
                    " )";

    public static final String SQL_SELECT_All_COLUMN =
            "SELECT *  FROM " + TABLE_NAME;

    private String id;
    private String lastMessage;
    private long lastMessageDateSent;
    private String photo;
    private String roomJid;
    private String sender;
    private String userLogin;
    private int readMessage;
    private String subject;
    private String occupants;
    private int type;
    private String conversationId;

    public CHRecentEntity() {

    }

    public CHRecentEntity(Object object) {
        CHRecentModel chRecentModel = (CHRecentModel) object;
        this.setId(chRecentModel.getId());
        this.setUserLogin(chRecentModel.getUserLogin());
        this.setSubject(chRecentModel.getSubject());
        this.setConversationId(chRecentModel.getConversationId());
        this.setSender(chRecentModel.getSender());
        this.setPhoto(chRecentModel.getPhoto());
        this.setLastMessage(chRecentModel.getLastMessage());
        this.setLastMessageDateSent(chRecentModel.getLastMessageDateSent());
        this.setReadMessage(chRecentModel.getReadMessage());
        this.setType(chRecentModel.getType());
        this.setConversationId(chRecentModel.getConversationId());
        if (null != chRecentModel.getOccupants()) {
            String opt = "";
            for (String str : chRecentModel.getOccupants()) {
                opt += str + ",";
            }
            this.setOccupants(opt.substring(0, opt.length() - 1));
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastMessage() {
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
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRoomJid() {
        return roomJid;
    }

    public void setRoomJid(String roomJid) {
        this.roomJid = roomJid;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getUserLogin() {
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
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOccupants() {
        return occupants;
    }

    public void setOccupants(String occupants) {
        this.occupants = occupants;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

}
