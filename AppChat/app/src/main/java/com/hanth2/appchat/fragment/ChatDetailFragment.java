package com.hanth2.appchat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanth2.appchat.R;
import com.hanth2.appchat.base.BaseFragment;

/**
 * Created by HanTH2 on 8/12/2016.
 */
public class ChatDetailFragment extends BaseFragment{
    public static ChatDetailFragment instance;

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
        return view;
    }
}
