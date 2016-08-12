package com.hanth2.appchat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanth2.appchat.ApplicationSingleton;
import com.hanth2.appchat.R;
import com.hanth2.appchat.adapter.ChatContactAdapter;
import com.hanth2.appchat.adapter.base.BaseRecyclerAdapter;
import com.hanth2.appchat.base.BaseFragment;
import com.hanth2.appchat.datastore.entities.CHUserContact;

import java.util.ArrayList;

/**
 * Created by HanTH2 on 8/12/2016.
 */
public class ContactFragment extends BaseFragment {
    public static ContactFragment instance;
    private ChatContactAdapter mAdapter = null;
    private RecyclerView mRecycleView;
    private LinearLayoutManager mLayoutManagerContact;

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
//        List<CHUserContact> CHUserContactList = userContactStoreController.getUserContactList(CHChatService.getInstance().getUser().getUname(), 10);
//        if (null == CHUserContactList) {
//            CHUserContactList = new ArrayList<>();
//        }
        mAdapter = new ChatContactAdapter(getActivity(), 0 , (ArrayList<CHUserContact>) ApplicationSingleton.mContactList);
        mAdapter.addAll(ApplicationSingleton.mContactList);
        mLayoutManagerContact = new LinearLayoutManager(getActivity());
        mLayoutManagerContact.setStackFromEnd(false);
        mRecycleView.setLayoutManager(mLayoutManagerContact);
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<CHUserContact>() {
            @Override
            public void onItemClick(View view, CHUserContact userContact, int i) {
                //switchGroupScreen(userContact.getFriend_name());
            }
        });
    }

    private void switchContactDetail(){

    }
}