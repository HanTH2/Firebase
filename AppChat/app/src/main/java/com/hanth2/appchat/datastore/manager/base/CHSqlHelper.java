package com.hanth2.appchat.datastore.manager.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hanth2.appchat.datastore.entities.CHChatMessage;
import com.hanth2.appchat.datastore.entities.CHRecentEntity;
import com.hanth2.appchat.datastore.entities.CHUserContact;

/**
 * Created by HanTH2 on 8/11/2016.
 */
public class CHSqlHelper extends SQLiteOpenHelper {

    private static CHSqlHelper instance = null;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Cantina.db";

    public static synchronized CHSqlHelper getInstance(Context context) {
        if (instance == null) {
            instance = new CHSqlHelper(context);
        }
        return instance;
    }

    public static CHSqlHelper getInstance() {
        return instance;
    }

    public CHSqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //update dataBase
        onDowngrade(getWritableDatabase(), 1, 1);
    }

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CHChatMessage.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_USER_RECENT =
            "DROP TABLE IF EXISTS " + CHRecentEntity.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_USER_CONTACT =
            "DROP TABLE IF EXISTS " + CHUserContact.TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CHChatMessage.SQL_CREATE_ENTRIES);
        db.execSQL(CHRecentEntity.SQL_CREATE_ENTRIES);
        db.execSQL(CHUserContact.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_USER_RECENT);
        db.execSQL(SQL_DELETE_ENTRIES_USER_CONTACT);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
