package com.hanth2.appchat.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hanth2.appchat.listenners.MainActivityListener;

/**
 * Created by HanTH2 on 8/12/2016.
 */
abstract public class BaseFragment extends Fragment {
    protected MainActivityListener mMainActivityListener;
    protected Context mContext;
    // [START declare_auth]
    protected FirebaseAuth mAuth;
    // [END declare_auth]

    protected FirebaseUser mFirebaseUser;
    protected DatabaseReference mFirebaseDatabaseReference;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMainActivityListener = (MainActivityListener)activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        mFirebaseUser = mAuth.getCurrentUser();
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

}
