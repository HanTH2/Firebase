package com.hanth2.appchat.chat;

import com.hanth2.appchat.controller.MessageController;
import com.hanth2.appchat.datastore.controller.MessageStoreController;
import com.hanth2.appchat.datastore.entities.CHChatMessage;

/**
 * Created by zero on 14/08/2016.
 */
public interface CHChat {
    void sendMessage(CHChatMessage message, String receive, String userLogin);
}
