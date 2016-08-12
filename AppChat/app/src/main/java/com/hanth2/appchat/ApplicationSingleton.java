package com.hanth2.appchat;

import android.app.Application;

import com.hanth2.appchat.datastore.entities.CHRecentEntity;
import com.hanth2.appchat.datastore.entities.CHUserContact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanTH2 on 8/12/2016.
 */
public class ApplicationSingleton extends Application {
    public static ApplicationSingleton instance;
    public static List<CHUserContact> mContactList = new ArrayList<>();

    public static ApplicationSingleton newInstance(){
        if (instance == null){
            instance = new ApplicationSingleton();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        for (int i = 0; i < 14; i++) {
            CHUserContact chUserContact = new CHUserContact();
            switch (i) {
                case 0:
                    String url_Avatar1 = "http://avatario.net/img/4.jpg";
                    chUserContact.setAvatar_friend_name(url_Avatar1);
                    chUserContact.setFriend_name("Ly Mac Sau");
                    break;
                case 1:
                    String url_Avatar2 = "http://avatario.net/img/1.jpg";
                    chUserContact.setAvatar_friend_name(url_Avatar2);
                    chUserContact.setFriend_name("Nguyen Manh Cam");
                    break;
                case 2:
                    String url_Avatar3 = "http://avatario.net/img/2.jpg";
                    chUserContact.setAvatar_friend_name(url_Avatar3);
                    chUserContact.setFriend_name("Nguyen Vu");
                    break;
                case 3:
                    String url_Avatar4 = "http://avatario.net/img/3.jpg";
                    chUserContact.setAvatar_friend_name(url_Avatar4);
                    chUserContact.setFriend_name("Duong Qua");
                    break;
                case 4:
                    String url_Avatar5 = "http://avatario.net/img/4.jpg";
                    chUserContact.setAvatar_friend_name(url_Avatar5);
                    chUserContact.setFriend_name("Ly Nhac");
                    break;
                case 5:
                    String url_Avatar6 = "http://avatario.net/img/5.jpg";
                    chUserContact.setAvatar_friend_name(url_Avatar6);
                    chUserContact.setFriend_name("Gia Cat Luong");
                    break;
                case 6:
                    String url_Avatar7 = "http://avatario.net/img/6.jpg";
                    chUserContact.setAvatar_friend_name(url_Avatar7);
                    chUserContact.setFriend_name("Khong Tu");
                    break;
                case 7:
                    String url_Avatar8 = "http://avatario.net/img/3.jpg";
                    chUserContact.setAvatar_friend_name(url_Avatar8);
                    chUserContact.setFriend_name("Tran Minh Tien");
                    break;
                case 8:
                    String url_Avatar9 = "http://avatario.net/img/6.jpg";
                    chUserContact.setAvatar_friend_name(url_Avatar9);
                    chUserContact.setFriend_name("Red Tigon");
                    break;
                case 9:
                    String url_Avatar10 = "http://avatario.net/img/6.jpg";
                    chUserContact.setAvatar_friend_name(url_Avatar10);
                    chUserContact.setFriend_name("Trang Tran");
                    break;
                case 10:
                    String url_Avatar11 = "http://avatario.net/img/6.jpg";
                    chUserContact.setAvatar_friend_name(url_Avatar11);
                    chUserContact.setFriend_name("Tran Ly Duong");
                    break;
                case 11:
                    String url_Avatar12 = "http://avatario.net/img/6.jpg";
                    chUserContact.setAvatar_friend_name(url_Avatar12);
                    chUserContact.setFriend_name("Nguyen Nguyen");
                    break;
                case 12:
                    String url_Avatar13 = "http://avatario.net/img/6.jpg";
                    chUserContact.setAvatar_friend_name(url_Avatar13);
                    chUserContact.setFriend_name("Tran Gia");
                    break;
                case 13:
                    String url_Avatar14 = "http://avatario.net/img/6.jpg";
                    chUserContact.setAvatar_friend_name(url_Avatar14);
                    chUserContact.setFriend_name("Ly Gia");
                    break;
            }
            mContactList.add(chUserContact);
        }
    }
}
