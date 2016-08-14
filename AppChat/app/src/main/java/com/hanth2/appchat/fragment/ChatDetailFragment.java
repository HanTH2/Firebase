package com.hanth2.appchat.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hanth2.appchat.R;
import com.hanth2.appchat.adapter.ChatAdapter;
import com.hanth2.appchat.adapter.base.BaseRecyclerAdapter;
import com.hanth2.appchat.base.BaseFragment;
import com.hanth2.appchat.base.BaseFragmentActivity;
import com.hanth2.appchat.chat.CHChat;
import com.hanth2.appchat.controller.MessageController;
import com.hanth2.appchat.datastore.controller.MessageStoreController;
import com.hanth2.appchat.datastore.controller.RecentStoreController;
import com.hanth2.appchat.datastore.entities.CHChatMessage;
import com.hanth2.appchat.datastore.model.CHRecentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanTH2 on 8/12/2016.
 */
public class ChatDetailFragment extends BaseFragment implements View.OnClickListener{
    private static final String TAG = ChatDetailFragment.class.getSimpleName();
    private static ChatDetailFragment instance;
    public static final String NAME_FRIEND_CHAT = "";
    public static final String USER_LOGIN = "";
    private String mNameFriendChat = "";
    private String mUserLogin = "";
    private TextView mTvUsernameFriendChat;
    private ImageButton mImageBtn;
    private EditText mEdtMsgChat;
    private CHChat mChat;
    private CHRecentModel mChRecentModel;
    private RecyclerView mRecyclerView;
    private ChatAdapter mAdapter;

    public static ChatDetailFragment newInstance(){
        if (instance == null){
            instance = new ChatDetailFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_chat, container, false);
        initView(view);
        initChatModel();
        return view;
    }

    private void initView(View view){
        mTvUsernameFriendChat = (TextView)view.findViewById(R.id.tv_username_friend_chat);
        mImageBtn = (ImageButton)view.findViewById(R.id.btn_back);
        mEdtMsgChat = (EditText)view.findViewById(R.id.edt_message);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.chat_details_RecyclerView);

        mNameFriendChat = getArguments().getString(NAME_FRIEND_CHAT, "nameChatDetail");
        mUserLogin = getArguments().getString(USER_LOGIN, "nameUserLogin");
        mTvUsernameFriendChat.setText(mNameFriendChat);

        long timeStart = 0;
        long timeEnd = System.currentTimeMillis();

        String conversation = mUserLogin.split("@")[0] + "-" + mNameFriendChat;
        List<CHChatMessage> chChatMessages = null;
        chChatMessages = MessageStoreController.getInstance()
                    .getMessageDataList(conversation, timeStart, timeEnd, 100);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ChatAdapter(mContext);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<CHChatMessage>() {
            @Override
            public void onItemClick(View var1, CHChatMessage chChatMessage, int position) {
                Log.e("Position click : ", position + "");
            }
        });

        mAdapter.addAll(chChatMessages == null ? new ArrayList<CHChatMessage>() : chChatMessages);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.smoothScrollToPosition(mRecyclerView.getAdapter().getItemCount());

        mImageBtn.setOnClickListener(this);
        mEdtMsgChat.setOnClickListener(this);
    }

    void initChatModel() {
        List<CHRecentModel> chRecentModelList = RecentStoreController.getInstance().getChatModelBySender(mNameFriendChat, mUserLogin, 10);
        if (null == chRecentModelList) {
            chRecentModelList = new ArrayList<>();
            Log.d(TAG, "chRecentModelList null");
        } else {
            if (chRecentModelList.isEmpty()) {
                mChRecentModel = new CHRecentModel();
                mChRecentModel.setSender(mNameFriendChat);
                mChRecentModel.setUserLogin(mUserLogin);
                mChRecentModel.setLastMessageDateSent(System.currentTimeMillis());
                try {
                    RecentStoreController.getInstance().add(mChRecentModel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                mChRecentModel = chRecentModelList.get(0);
            }
            //mChRecentModel.setRoomJid(mNameFriendChat);
        }

    }

    private void sendMessageChat(String messageText, String receive, String userLogin){
        CHChatMessage chChatMessage = new CHChatMessage(messageText);
        mChat.sendMessage(chChatMessage, new MessageController(), receive, userLogin);
        mEdtMsgChat.setText("");
    }

    public void showMessage(final CHChatMessage chChatMessage) {
        mAdapter.add(chChatMessage);
        mAdapter.notifyDataSetChanged();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.smoothScrollToPosition(mRecyclerView.getAdapter().getItemCount());
                Log.d(TAG, "show message");
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                mMainActivityListener.onBackChatDetail();
                break;
            case R.id.edt_message:
                sendMessageChat(mEdtMsgChat.getText().toString(), mNameFriendChat, mUserLogin);
                break;
            default:
                break;
        }
    }
}
