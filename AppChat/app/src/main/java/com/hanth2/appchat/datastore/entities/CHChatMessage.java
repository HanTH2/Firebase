package com.hanth2.appchat.datastore.entities;

/**
 * Created by HanTH2 on 8/11/2016.
 */
public class CHChatMessage {
    //TODO bo sung table conversation
    // có thể bỏ qua phần conversation, thêm trực tiếp vào roomId


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static final String TABLE_NAME = "mdata";
    public static final String _ID = "_id";
    public static final String _SENDER = "_sender";
    public static final String _RECEIVER = "_receiver";
    public static final String _BODY = "_body";
    public static final String _TIME = "_time";
    public static final String _TYPE = "_type";
    public static final String _ROOM_ID = "_roomId";
    public static final String _CONVERSATION_ID = "_conversationId";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    _SENDER + TEXT_TYPE + COMMA_SEP +
                    _RECEIVER + TEXT_TYPE + COMMA_SEP +
                    _BODY + TEXT_TYPE + COMMA_SEP +
                    _TIME + TEXT_TYPE + COMMA_SEP +
                    _TYPE + TEXT_TYPE + COMMA_SEP +
                    _ROOM_ID + TEXT_TYPE + COMMA_SEP +
                    _CONVERSATION_ID + TEXT_TYPE +
                    " )";


    public static final String SQL_SELECT_All_COLUMN =
            "SELECT *  FROM " + TABLE_NAME;



    public String sender;
    public String receiver;
    public long time;
    public String roomId;
    public String conversationId;

    public int msgId;
    public String updateAt;
    public String deleteAt;
    public String createAt;
    public String userId;
    public String channelId;
    public String rootId;
    public String parentId;
    public String originalId;
    public String body;
    public int type;
    public String props;
    public String hashtags;
    public String fileNames;
    public String imgUrl;

    // add friend contact
    private String id;
    private String friend_name;
    private String avatar_friend_name;

    public String getAvatar_sender() {
        return avatar_sender;
    }

    public void setAvatar_sender(String avatar_sender) {
        this.avatar_sender = avatar_sender;
    }

    public String getAvatar_friend_name() {
        return avatar_friend_name;
    }

    public void setAvatar_friend_name(String avatar_friend_name) {
        this.avatar_friend_name = avatar_friend_name;
    }

    private String avatar_sender;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFriend_name() {
        return friend_name;
    }

    public void setFriend_name(String friend_name) {
        this.friend_name = friend_name;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(String deleteAt) {
        this.deleteAt = deleteAt;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getProps() {
        return props;
    }

    public void setProps(String props) {
        this.props = props;
    }

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }

    public String getFileNames() {
        return fileNames;
    }

    public void setFileNames(String fileNames) {
        this.fileNames = fileNames;
    }

    public CHChatMessage() {
    }

    public CHChatMessage(String body) {
        this.body = body;
    }

    public CHChatMessage(String conversationId, String sender, String receiver, String msg, long time) {
        this.body = msg;
        this.sender = sender;
        this.receiver = receiver;
        this.time = time;
        this.conversationId = conversationId;
    }


    public CHChatMessage(String conversationId, String body, String sender, String receiver, long time, String roomId) {
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
        this.time = time;
        this.roomId = roomId;
        this.conversationId = conversationId;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
