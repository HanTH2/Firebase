package com.hanth2.appchat.datastore.manager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hanth2.appchat.datastore.entities.CHChatMessage;
import com.hanth2.appchat.datastore.manager.base.CHSqlHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanTH2 on 8/11/2016.
 */
public class MessageManager {

    private static MessageManager instance;


    public static synchronized MessageManager getInstance() {
        if (null == instance)
            instance = new MessageManager();
        return instance;
    }

    public MessageManager() {
    }

    public void addMessageData(CHChatMessage CHChatMessage) {
        SQLiteDatabase db = CHSqlHelper.getInstance().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CHChatMessage._CONVERSATION_ID, CHChatMessage.getConversationId());
        values.put(CHChatMessage._SENDER, CHChatMessage.getSender());
        values.put(CHChatMessage._RECEIVER, CHChatMessage.getReceiver());
        values.put(CHChatMessage._BODY, CHChatMessage.getBody());
        values.put(CHChatMessage._TIME, CHChatMessage.getTime());
        values.put(CHChatMessage._TYPE, CHChatMessage.getType());
        values.put(CHChatMessage._ROOM_ID, CHChatMessage.getRoomId() == null ? String.valueOf(-1) : CHChatMessage.getRoomId());
        db.insert(CHChatMessage.TABLE_NAME, null, values);
        db.close();
    }


    public CHChatMessage getMessageData(String mId) {
        SQLiteDatabase db = CHSqlHelper.getInstance().getReadableDatabase();

        Cursor cursor = db.query(CHChatMessage.TABLE_NAME, new String[]{CHChatMessage._ID,
                        CHChatMessage._SENDER, CHChatMessage._RECEIVER, CHChatMessage._BODY, CHChatMessage._TIME}, CHChatMessage._ID + "=?",
                new String[]{String.valueOf(mId)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        CHChatMessage CHChatMessage = new CHChatMessage(
                cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._CONVERSATION_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._SENDER)),
                cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._RECEIVER)),
                cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._BODY)),
                Long.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._TIME))));
        // return contact
        return CHChatMessage;
    }

    public List<CHChatMessage> getListMessageData(String conversationId, long timeStart, long timeEnd, int max) {
        SQLiteDatabase db = CHSqlHelper.getInstance().getReadableDatabase();
        List<CHChatMessage> CHChatMessageList = null;
        Cursor cursor = db.rawQuery(CHChatMessage.SQL_SELECT_All_COLUMN + " WHERE ("
                + CHChatMessage._TIME + " BETWEEN '"
                + timeStart + "' AND '" + timeEnd + "')"
                + " AND  " + CHChatMessage._ROOM_ID + "= -1 AND " + CHChatMessage._CONVERSATION_ID + "='" + conversationId + "'"
                + " ORDER BY " + CHChatMessage._TIME + " ASC LIMIT " + max, null);
        if (cursor != null)
            if (cursor.moveToFirst()) {
                CHChatMessageList = new ArrayList<>();
                do {
                    try {
                        CHChatMessage CHChatMessage = new CHChatMessage(
                                cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._CONVERSATION_ID)),
                                cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._SENDER)),
                                cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._RECEIVER)),
                                cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._BODY)),
                                Long.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._TIME))));
                        CHChatMessageList.add(CHChatMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (cursor.moveToNext());
            }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return CHChatMessageList;
    }


    public List<CHChatMessage> getListMessageData(long timeStart, long timeEnd, int max, String roomId) {
        SQLiteDatabase db = CHSqlHelper.getInstance().getReadableDatabase();
        List<CHChatMessage> CHChatMessageList = null;
        Cursor cursor = db.rawQuery(CHChatMessage.SQL_SELECT_All_COLUMN + " WHERE ("
                + CHChatMessage._TIME + " BETWEEN '"
                + timeStart + "' AND '" + timeEnd + "' )"
                + "AND  " + CHChatMessage._ROOM_ID + "='" + roomId + "'"
                + " ORDER BY " + CHChatMessage._TIME + " ASC LIMIT " + max, null);
        if (cursor != null)
            if (cursor.moveToFirst()) {
                CHChatMessageList = new ArrayList<>();
                do {
                    try {
                        CHChatMessage CHChatMessage = new CHChatMessage(
                                cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._CONVERSATION_ID)),
                                cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._BODY)),
                                cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._SENDER)),
                                cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._RECEIVER)),
                                Long.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._TIME))),
                                cursor.getString(cursor.getColumnIndexOrThrow(com.hanth2.appchat.datastore.entities.CHChatMessage._ROOM_ID)));
                        CHChatMessageList.add(CHChatMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (cursor.moveToNext());
            }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return CHChatMessageList;
    }


}
