package com.hanth2.appchat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hanth2.appchat.R;
import com.hanth2.appchat.adapter.viewholder.MessageViewHolder;
import com.hanth2.appchat.base.BaseFragment;
import com.hanth2.appchat.chat.CHChat;
import com.hanth2.appchat.datastore.entities.CHChatMessage;
import com.hanth2.appchat.datastore.model.CHRecentModel;
import com.hanth2.appchat.utils.ConvertTime;
import com.squareup.picasso.Picasso;

/**
 * Created by HanTH2 on 8/12/2016.
 */
public class ChatDetailFragment extends BaseFragment implements View.OnClickListener{
    private static final String TAG = ChatDetailFragment.class.getSimpleName();
    private static ChatDetailFragment instance;
    public static final String NAME_FRIEND_CHAT = "NAME_FRIEND_CHAT";
    public static final String ID_FRIEND_CHAT = "ID_FRIEND_CHAT";
    private String mChatId = "";
    private String mUserLogin = "";
    private TextView mTvUsernameFriendChat;
    private ImageButton mImgBackBtn;
    private EditText mEdtMsgChat;
    private CHChat mChat;
    private CHRecentModel mChRecentModel;
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<CHChatMessage, MessageViewHolder> mFirebaseAdapter;
    public static final String MESSAGES_CHILD = "messages";
    private ImageButton mImgSend;
    private ProgressBar mProgressBar;
    private LinearLayoutManager mLinearLayoutManager;
    private String mPhotoUrl;

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
        return view;
    }

    private void initView(View view){
        mTvUsernameFriendChat = (TextView)view.findViewById(R.id.tv_username_friend_chat);
        mImgBackBtn = (ImageButton)view.findViewById(R.id.btn_back);
        mEdtMsgChat = (EditText)view.findViewById(R.id.edt_message);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.chat_details_RecyclerView);
        mImgSend = (ImageButton)view.findViewById(R.id.img_send);
        mProgressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        mImgSend = (ImageButton)view.findViewById(R.id.img_send);

        mUserLogin = mFirebaseUser.getEmail();
        mTvUsernameFriendChat.setText(getArguments().getString(NAME_FRIEND_CHAT));

        String userId = mFirebaseUser.getUid();
        String friendId = getArguments().getString(ID_FRIEND_CHAT);
        if (userId.compareTo(friendId) < 0) {
            mChatId= userId + "_" + friendId;
        } else {
            mChatId= friendId + "_" + userId;
        }
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mLinearLayoutManager.setStackFromEnd(true);
        mFirebaseAdapter = new FirebaseRecyclerAdapter<CHChatMessage, MessageViewHolder>(
                CHChatMessage.class,
                R.layout.recycler_message_item,
                MessageViewHolder.class,
                mFirebaseDatabaseReference.child(MESSAGES_CHILD).child(mChatId)) {

            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, CHChatMessage chChatMessage, int position) {
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                viewHolder.message_sender.setText(chChatMessage.getBody());
                viewHolder.name_sender.setText(chChatMessage.getSender());
                viewHolder.time_sender.setText(ConvertTime.convertTimestamp(chChatMessage.getTime()));
                if (chChatMessage.getAvatar_sender() == null) {
                    viewHolder.avatar_sender.setImageDrawable(ContextCompat.getDrawable(mContext,
                            R.drawable.ic_account_circle_black_36dp));
                } else {
                    Picasso.with(mContext)
                            .load(chChatMessage.getAvatar_sender())
                            .into(viewHolder.avatar_sender);
                }
            }
        };
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mFirebaseAdapter);

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the user is at the bottom of the list, scroll
                // to the bottom of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) && lastVisiblePosition == (positionStart - 1))) {
                    mRecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        mImgBackBtn.setOnClickListener(this);
        mEdtMsgChat.setOnClickListener(this);
        mImgSend.setOnClickListener(this);
    }

    private void sendMessageChat(String messageText, String userLogin){
        //mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
        mPhotoUrl = "http://avatario.net/img/1.jpg";
        CHChatMessage chChatMessage = new CHChatMessage(messageText, mUserLogin,
                mPhotoUrl);
        mFirebaseDatabaseReference.child(MESSAGES_CHILD).child(mChatId).push().setValue(chChatMessage);
        mEdtMsgChat.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                mMainActivityListener.onBackChatDetail();
                break;
            case R.id.img_send:
                sendMessageChat(mEdtMsgChat.getText().toString(), mUserLogin);
                break;
            default:
                break;
        }
    }
}
