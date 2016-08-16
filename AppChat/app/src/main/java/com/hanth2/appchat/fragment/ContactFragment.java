package com.hanth2.appchat.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.hanth2.appchat.R;
import com.hanth2.appchat.adapter.ChatContactAdapter;
import com.hanth2.appchat.base.BaseFragment;
import com.hanth2.appchat.constant.AppConstants;
import com.hanth2.appchat.datastore.entities.CHUserContact;
import com.squareup.picasso.Picasso;

/**
 * Created by HanTH2 on 8/12/2016.
 */
public class ContactFragment extends BaseFragment{
    public static ContactFragment instance;
    private FirebaseRecyclerAdapter<CHUserContact, ChatContactAdapter.RecyclerItemViewHolder> mFirebaseAdapter;
    private RecyclerView mRecycleView;
    private LinearLayoutManager mLayoutManagerContact;
    private String mPhotoUrl = "";

    public static ContactFragment newInstance(){
        if (instance == null){
            instance = new ContactFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        initView(view);
        initRecyclerViewContact();
        return view;
    }

    private void initView(View view){
        mRecycleView = (RecyclerView)view.findViewById(R.id.recycler_contact);
    }

    private void initRecyclerViewContact() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<CHUserContact, ChatContactAdapter.RecyclerItemViewHolder>(
                CHUserContact.class,
                R.layout.recycler_contact_item,
                ChatContactAdapter.RecyclerItemViewHolder.class,
                mFirebaseDatabaseReference.child(AppConstants.DATA.ACCOUNT)) {

            @Override
            protected void populateViewHolder(ChatContactAdapter.RecyclerItemViewHolder viewHolder, final CHUserContact chUserContact, int position) {
               // mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                viewHolder.contactName.setText(chUserContact.getFriend_name());
                Picasso.with(mContext).load(chUserContact.getAvatar_sender()).into(viewHolder.avatar);
                viewHolder.contactName.setTextColor(Color.BLACK);
                if (chUserContact.getFriend_name().equalsIgnoreCase(mFirebaseUser.getEmail())) {
                    viewHolder.contactName.setText(chUserContact.getFriend_name() + "(me)");
                    mPhotoUrl = chUserContact.getAvatar_sender();
                    return;
                }
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switchChatDetailScreen();
                        Bundle bundle = new Bundle();
                        bundle.putString(ChatDetailFragment.NAME_FRIENDS_CHAT, chUserContact.getFriend_name());
                        bundle.putString(ChatDetailFragment.ID_FRIENDS_CHAT, chUserContact.getId());
                        bundle.putString(ChatDetailFragment.URL_PHOTO_SENDER_CHAT, mPhotoUrl);
                        ChatDetailFragment chatDetailFragment = ChatDetailFragment.newInstance();
                        chatDetailFragment.setArguments(bundle);
                    }
                });
            }
        };
        mLayoutManagerContact = new LinearLayoutManager(getActivity());
        mLayoutManagerContact.setStackFromEnd(false);
        mRecycleView.setLayoutManager(mLayoutManagerContact);
        mRecycleView.setAdapter(mFirebaseAdapter);
    }

    private void switchChatDetailScreen(){
        mMainActivityListener.attachShowChatDetailFr();
    }
}
