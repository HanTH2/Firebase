package com.hanth2.appchat.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import com.hanth2.appchat.listenners.MainActivityListener;

/**
 * Created by HanTH2 on 8/12/2016.
 */
abstract public class BaseFragment extends Fragment {
    protected MainActivityListener mMainActivityListener;
    protected Context mContext;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMainActivityListener = (MainActivityListener)activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    protected void signOutFirebase(){
        ((BaseActivity)getActivity()).signOutFirebase();
    }
}
