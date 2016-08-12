package com.hanth2.appchat.datastore.controller;

import android.util.Log;

import com.hanth2.appchat.datastore.entities.CHRecentEntity;
import com.hanth2.appchat.datastore.manager.RecentStoreManager;
import com.hanth2.appchat.datastore.model.CHRecentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanTH2 on 8/11/2016.
 */
public class RecentStoreController {
    private static final String TAG = RecentStoreController.class.getSimpleName();
    public static RecentStoreController instance = null;

    public static synchronized RecentStoreController getInstance() {
        if (instance == null) {
            instance = new RecentStoreController();
        }
        return instance;
    }

    public RecentStoreController() {

    }

    public void add(CHRecentModel chRecentModel) throws Exception {
        CHRecentEntity chRecentEntity = new CHRecentEntity(chRecentModel);
        RecentStoreManager.getInstance().addUserRecent(chRecentEntity);
        Log.e(TAG, "Store userRecent Success !");
    }

    public List<CHRecentModel> getUserRecentList(String userLogin, int max) {
        List<CHRecentEntity> chRecentEntities = RecentStoreManager.getInstance().getUserRecentList(userLogin, max);
        List<CHRecentModel> chRecentModels = new ArrayList<>();
        if (null != chRecentEntities) {
            for (CHRecentEntity chRecentEntity : chRecentEntities) {
                CHRecentModel chRecentModel = new CHRecentModel(chRecentEntity);
                chRecentModels.add(chRecentModel);
            }
        }
        return chRecentModels;
    }

    public List<CHRecentModel> getChatModelBySender(String sender, String userLogin, int max) {
        List<CHRecentEntity> chRecentEntities = RecentStoreManager.getInstance().getUserRecentList(sender, userLogin, max);
        List<CHRecentModel> chRecentModels = new ArrayList<>();
        if (null != chRecentEntities) {
            for (CHRecentEntity chRecentEntity : chRecentEntities) {
                CHRecentModel chRecentModel = new CHRecentModel(chRecentEntity);
                chRecentModels.add(chRecentModel);
            }
        }
        return chRecentModels;
    }


}
