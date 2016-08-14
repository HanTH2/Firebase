package com.hanth2.appchat.chat;

import com.hanth2.appchat.controller.MessageController;
import com.hanth2.appchat.datastore.controller.MessageStoreController;
import com.hanth2.appchat.datastore.entities.CHChatMessage;

/**
 * Created by zero on 14/08/2016.
 */
public class PrivateChatImpl implements CHChat {

    @Override
    public void sendMessage(CHChatMessage chChatMessage, MessageController messageController, String receive, String userLogin) {

        String conversation = userLogin.split("@")[0] + "-" + receive;
        chChatMessage.setConversationId(conversation);
        chChatMessage.setSender(receive);
        chChatMessage.setReceiver(receive);
        chChatMessage.setTime(System.currentTimeMillis());
        try {
            MessageStoreController.getInstance().add(chChatMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
