package com.hanth2.appchat.datastore.manager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hanth2.appchat.datastore.entities.CHUserContact;
import com.hanth2.appchat.datastore.manager.base.CHSqlHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanTH2 on 8/11/2016.
 */
public class UserContactManager {
    private static UserContactManager instance;

    public static synchronized UserContactManager getInstance(){
        if (null == instance){
            instance = new UserContactManager();
        }
        return instance;
    }

    public UserContactManager(){

    }

    public void addUserContact(CHUserContact chUserContact) {
        removeUserContact(chUserContact);
        SQLiteDatabase db = CHSqlHelper.getInstance().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CHUserContact._USER_LOGIN, chUserContact.getUser_login());
        values.put(CHUserContact._FRIEND_NAME, chUserContact.getFriend_name());
        values.put(CHUserContact._AVATAR_FRIEND_NAME, chUserContact.getAvatar_friend_name());
        db.insert(CHUserContact.TABLE_NAME, null, values);
        db.close();
    }

    public void removeUserContact(CHUserContact chUserContact) {
        SQLiteDatabase db = CHSqlHelper.getInstance().getWritableDatabase();
        db.delete(chUserContact.TABLE_NAME, CHUserContact._USER_LOGIN + "=" + chUserContact.getUser_login() + " AND " + CHUserContact._FRIEND_NAME + "='" + chUserContact.getFriend_name() + "'", null);
        db.close();
    }

    public List<CHUserContact> getUserContactList(String userLogin, int max) {
        SQLiteDatabase db = CHSqlHelper.getInstance().getReadableDatabase();
        List<CHUserContact> CHUserContactList = null;
        Cursor cursor = db.rawQuery(CHUserContact.SQL_SELECT_ALL_COLUMN + " WHERE " + CHUserContact._USER_LOGIN + "='" + userLogin +
                "' ORDER BY " + CHUserContact._FRIEND_NAME + " DESC LIMIT " + max, null);
        if (cursor != null)
            if (cursor.moveToFirst()) {
                CHUserContactList = new ArrayList<>();
                do {
                    try {
                        CHUserContact chUserContact = new CHUserContact();
                        chUserContact.setFriend_name(cursor.getString(cursor.getColumnIndexOrThrow(CHUserContact._USER_LOGIN)));
                        chUserContact.setFriend_name(cursor.getString(cursor.getColumnIndexOrThrow(CHUserContact._FRIEND_NAME)));
                        chUserContact.setAvatar_friend_name(cursor.getString(cursor.getColumnIndexOrThrow(CHUserContact._AVATAR_FRIEND_NAME)));
                        CHUserContactList.add(chUserContact);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (cursor.moveToNext());
            }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return CHUserContactList;
    }


    public List<CHUserContact> getUserContactList(String friend, String userLogin) {
        SQLiteDatabase db = CHSqlHelper.getInstance().getReadableDatabase();
        List<CHUserContact> CHUserContactList = null;
        Cursor cursor = db.rawQuery(CHUserContact.SQL_SELECT_ALL_COLUMN + " WHERE " + CHUserContact._USER_LOGIN + "=" + userLogin +
                " AND " + CHUserContact._FRIEND_NAME + "=" + friend , null);
        if (cursor != null)
            if (cursor.moveToFirst()) {
                CHUserContactList = new ArrayList<>();
                do {
                    try {
                        CHUserContact chUserContact = new CHUserContact();
                        chUserContact.setFriend_name(cursor.getString(cursor.getColumnIndexOrThrow(CHUserContact._USER_LOGIN)));
                        chUserContact.setFriend_name(cursor.getString(cursor.getColumnIndexOrThrow(CHUserContact._FRIEND_NAME)));
                        chUserContact.setAvatar_friend_name(cursor.getString(cursor.getColumnIndexOrThrow(CHUserContact._AVATAR_FRIEND_NAME)));
                        CHUserContactList.add(chUserContact);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (cursor.moveToNext());
            }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return CHUserContactList;
    }
}
