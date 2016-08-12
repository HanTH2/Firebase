package com.hanth2.appchat.datastore.manager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hanth2.appchat.datastore.entities.CHRecentEntity;
import com.hanth2.appchat.datastore.manager.base.CHSqlHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanTH2 on 8/11/2016.
 */
public class RecentStoreManager {
    private static RecentStoreManager instance;

    public static synchronized RecentStoreManager getInstance() {
        if (null == instance)
            instance = new RecentStoreManager();
        return instance;
    }

    public RecentStoreManager() {

    }

    public void addUserRecent(CHRecentEntity CHRecentEntity) {
        removeUserRecent(CHRecentEntity);
        SQLiteDatabase db = CHSqlHelper.getInstance().getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CHRecentEntity._SENDER, CHRecentEntity.getSender());
        values.put(CHRecentEntity._PHOTO, CHRecentEntity.getPhoto());
        values.put(CHRecentEntity._USERLOGIN, CHRecentEntity.getUserLogin());
        values.put(CHRecentEntity._READMESSAGE, CHRecentEntity.getReadMessage());
        values.put(CHRecentEntity._LASTMESSAGE, CHRecentEntity.getLastMessage());
        values.put(CHRecentEntity._LASTMESSAGEDATESENT, CHRecentEntity.getLastMessageDateSent());
        values.put(CHRecentEntity._ROOMJID, CHRecentEntity.getRoomJid());
        values.put(CHRecentEntity._CONVERATIONID, CHRecentEntity.getConversationId());
        values.put(CHRecentEntity._TYPE, CHRecentEntity.getType());
        values.put(CHRecentEntity._SUBJECT, CHRecentEntity.getSubject());
        values.put(CHRecentEntity._OCCUPANTS, CHRecentEntity.getOccupants());

        db.insert(CHRecentEntity.TABLE_NAME, null, values);
//        db.close();
    }

    public void removeUserRecent(CHRecentEntity CHRecentEntity) {
        SQLiteDatabase db = CHSqlHelper.getInstance().getWritableDatabase();
        db.delete(CHRecentEntity.TABLE_NAME, CHRecentEntity._SENDER + "='" + CHRecentEntity.getSender() + "' AND " + CHRecentEntity._USERLOGIN + "='" + CHRecentEntity.getUserLogin() + "'", null);
//        db.close();
    }


    public List<CHRecentEntity> getUserRecentList(String userLogin, int max) {
        try {
            SQLiteDatabase db = CHSqlHelper.getInstance().getReadableDatabase();
            List<CHRecentEntity> CHRecentEntityList = null;
            Cursor cursor = db.rawQuery(CHRecentEntity.SQL_SELECT_All_COLUMN + " WHERE " + CHRecentEntity._USERLOGIN + "='" + userLogin +
                    "' ORDER BY " + CHRecentEntity._LASTMESSAGEDATESENT + " DESC LIMIT " + max, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    CHRecentEntityList = new ArrayList<>();
                    do {
                        try {
                            CHRecentEntity CHRecentEntity = new CHRecentEntity();
                            CHRecentEntity.setId(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._ID)));
                            CHRecentEntity.setSender(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._SENDER)));
                            CHRecentEntity.setPhoto(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._PHOTO)));
                            CHRecentEntity.setUserLogin(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._USERLOGIN)));
                            CHRecentEntity.setReadMessage(cursor.getInt(cursor.getColumnIndexOrThrow(CHRecentEntity._READMESSAGE)));
                            CHRecentEntity.setLastMessage(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._LASTMESSAGE)));
                            CHRecentEntity.setLastMessageDateSent(cursor.getLong(cursor.getColumnIndexOrThrow(CHRecentEntity._LASTMESSAGEDATESENT)));
                            CHRecentEntity.setRoomJid(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._ROOMJID)));
                            CHRecentEntity.setConversationId(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._CONVERATIONID)));
                            CHRecentEntity.setType(cursor.getInt(cursor.getColumnIndexOrThrow(CHRecentEntity._TYPE)));
                            CHRecentEntity.setSubject(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._SUBJECT)));
                            CHRecentEntity.setOccupants(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._OCCUPANTS)));
                            CHRecentEntityList.add(CHRecentEntity);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } while (cursor.moveToNext());
                }
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
//            db.close();
            return CHRecentEntityList;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<CHRecentEntity> getUserRecentList(String sender, String userLogin, int max) {
        try {
            SQLiteDatabase db = CHSqlHelper.getInstance().getReadableDatabase();
            List<CHRecentEntity> CHRecentEntityList = null;
            Cursor cursor = db.rawQuery(CHRecentEntity.SQL_SELECT_All_COLUMN + " WHERE " + CHRecentEntity._USERLOGIN + "='" + userLogin +
                    "' AND " + CHRecentEntity._SENDER + "='" + sender + "' ORDER BY " + CHRecentEntity._LASTMESSAGEDATESENT + " DESC LIMIT " + max, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    CHRecentEntityList = new ArrayList<>();
                    do {
                        try {
                            CHRecentEntity CHRecentEntity = new CHRecentEntity();
                            CHRecentEntity.setId(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._ID)));
                            CHRecentEntity.setSender(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._SENDER)));
                            CHRecentEntity.setPhoto(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._PHOTO)));
                            CHRecentEntity.setUserLogin(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._USERLOGIN)));
                            CHRecentEntity.setReadMessage(cursor.getInt(cursor.getColumnIndexOrThrow(CHRecentEntity._READMESSAGE)));
                            CHRecentEntity.setLastMessage(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._LASTMESSAGE)));
                            CHRecentEntity.setLastMessageDateSent(cursor.getLong(cursor.getColumnIndexOrThrow(CHRecentEntity._LASTMESSAGEDATESENT)));
                            CHRecentEntity.setRoomJid(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._ROOMJID)));
                            CHRecentEntity.setConversationId(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._CONVERATIONID)));
                            CHRecentEntity.setType(cursor.getInt(cursor.getColumnIndexOrThrow(CHRecentEntity._TYPE)));
                            CHRecentEntity.setSubject(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._SUBJECT)));
                            CHRecentEntity.setOccupants(cursor.getString(cursor.getColumnIndexOrThrow(CHRecentEntity._OCCUPANTS)));
                            CHRecentEntityList.add(CHRecentEntity);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } while (cursor.moveToNext());
                }
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
//            db.close();
            return CHRecentEntityList;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


}
