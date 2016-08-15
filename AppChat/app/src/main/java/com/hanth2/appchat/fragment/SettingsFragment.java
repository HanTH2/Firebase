package com.hanth2.appchat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hanth2.appchat.R;
import com.hanth2.appchat.base.BaseFragment;

/**
 * Created by HanTH2 on 8/12/2016.
 */
public class SettingsFragment extends BaseFragment implements View.OnClickListener {
    public static SettingsFragment instance;
    private Button mBtnLogout;

    public static SettingsFragment newIntance(){
        if (instance == null){
            instance = new SettingsFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBtnLogout = (Button) view.findViewById(R.id.btn_logout);
        mBtnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_logout:
                mAuth.signOut();
                break;
            default:
                break;
        }
    }
}
