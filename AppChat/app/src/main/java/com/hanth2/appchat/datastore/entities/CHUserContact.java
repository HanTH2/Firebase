package com.hanth2.appchat.datastore.entities;

/**
 * Created by HanTH2 on 8/11/2016.
 */
public class CHUserContact {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static final String TABLE_NAME = "user_contact";
    public static final String _ID = "_id";
    public static final String _USER_LOGIN = "user_login";
    public static final String _FRIEND_NAME = "_friend_name";
    public static final String _AVATAR_FRIEND_NAME = "_avatar_friend_name";
    /**
     * 0 request
     * 1 accept
     * 2 deny
     */
    public static final String _STATUS = "_status";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    _ID + "INTEGER PRIMARY KEY," +
                    _USER_LOGIN + TEXT_TYPE + COMMA_SEP +
                    _FRIEND_NAME + TEXT_TYPE + COMMA_SEP +
                    _AVATAR_FRIEND_NAME + TEXT_TYPE + COMMA_SEP +
                    _STATUS + TEXT_TYPE + ")";

    public static final String SQL_SELECT_ALL_COLUMN =
            "SELECT * FROM " + TABLE_NAME;

    private String id;
    private String friend_name;
    private String avatar_friend_name;
    private String user_login;
    private CHUserContact chUserContact;

    public boolean isCbCheck() {
        return cbCheck;
    }

    public void setCbCheck(boolean cbCheck) {
        this.cbCheck = cbCheck;
    }

    private boolean cbCheck = false;

    public CHUserContact(){

    }

    public CHUserContact(CHUserContact chUserContact){
        this.chUserContact = chUserContact;
    }

    public CHUserContact(String friend_name){
        this.friend_name = friend_name;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getAvatar_friend_name() {
        return avatar_friend_name;
    }

    public void setAvatar_friend_name(String avatar_friend_name) {
        this.avatar_friend_name = avatar_friend_name;
    }

    public String getFriend_name() {
        return friend_name;
    }

    public void setFriend_name(String friend_name) {
        this.friend_name = friend_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
