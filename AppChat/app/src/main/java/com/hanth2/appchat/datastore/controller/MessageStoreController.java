package com.hanth2.appchat.datastore.controller;
import com.hanth2.appchat.datastore.entities.CHChatMessage;
import com.hanth2.appchat.datastore.manager.MessageManager;

import java.util.List;

/**
 * Created by HanTH2 on 8/11/2016.
 */
public class MessageStoreController {
    public static MessageStoreController instance = null;
    MessageManager messageManager;

    public static synchronized MessageStoreController getInstance() {
        if (instance == null) {
            instance = new MessageStoreController();
        }
        return instance;
    }

    public MessageStoreController() {
        messageManager = MessageManager.getInstance();
    }


    public void add(CHChatMessage CHChatMessage) throws Exception {
        messageManager.addMessageData(CHChatMessage);
    }

    public CHChatMessage getMessageData(String mId) {
        CHChatMessage CHChatMessage = messageManager.getMessageData(mId);
        return CHChatMessage;
    }

    public List<CHChatMessage> getMessageDataList(String conversationId, long timeStart, long timeEnd, int max) {
        List<CHChatMessage> CHChatMessageList = messageManager.getListMessageData(conversationId, timeStart, timeEnd, max);
        return CHChatMessageList;
    }

    public List<CHChatMessage> getMessageDataRoom(long timeStart, long timeEnd, int max, String roomId) {
        List<CHChatMessage> CHChatMessageList = messageManager.getListMessageData(timeStart, timeEnd, max, roomId);
        return CHChatMessageList;
    }

}
