package com.hanth2.appchat.datastore.controller;
import com.hanth2.appchat.datastore.entities.CHUserContact;
import com.hanth2.appchat.datastore.manager.UserContactManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanTH2 on 8/11/2016.
 */
public class UserContactStoreController {

    public static UserContactStoreController instance = null;

    public static synchronized UserContactStoreController getInstance() {
        if (instance == null) {
            instance = new UserContactStoreController();
        }
        return instance;
    }

    public UserContactStoreController() {

    }

    public void add(CHUserContact chUserContact) throws Exception {
        UserContactManager.getInstance().addUserContact(chUserContact);
    }

    public List<CHUserContact> getUserContactList(String userLogin, int max) {
        List<CHUserContact> CHUserContactList = UserContactManager.getInstance().getUserContactList(userLogin, max);
        return CHUserContactList;
    }

    public CHUserContact getUserContact(String friend, String userLogin) {
        List<CHUserContact> chUserContactList = UserContactManager.getInstance().getUserContactList(friend, userLogin);
        List<CHUserContact> chUserContactList1 = new ArrayList<>();
        if (null != chUserContactList) {
            for (CHUserContact chUserContact : chUserContactList) {
                CHUserContact chUserContactItem = new CHUserContact(chUserContact);
                chUserContactList1.add(chUserContactItem);
            }
        }
        if (chUserContactList == null || chUserContactList.isEmpty()){
            return null;
        }else {
            return chUserContactList1.get(0);
        }
    }

}
