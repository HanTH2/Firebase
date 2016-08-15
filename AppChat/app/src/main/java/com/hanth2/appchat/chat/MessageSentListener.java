package com.hanth2.appchat.chat;

import com.hanth2.appchat.datastore.entities.CHChatMessage;

/**
 * Created by zero on 14/08/2016.
 */
public interface MessageSentListener {
    void messageSendSuccess(CHChatMessage chChatMessage);
    void messageSentFailed(Exception error);
}
