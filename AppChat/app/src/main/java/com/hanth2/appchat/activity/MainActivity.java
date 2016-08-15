package com.hanth2.appchat.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hanth2.appchat.R;
import com.hanth2.appchat.base.BaseFragmentActivity;
import com.hanth2.appchat.constant.AppConstants;
import com.hanth2.appchat.fragment.ChatDetailFragment;
import com.hanth2.appchat.fragment.ContactFragment;
import com.hanth2.appchat.fragment.HomeFragment;
import com.hanth2.appchat.fragment.RecentFragment;
import com.hanth2.appchat.fragment.SettingsFragment;
import com.hanth2.appchat.listenners.MainActivityListener;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener, MainActivityListener {
    private RadioButton mBtnHome, mBtnRecent, mBtnGroups, mBtnSettings;
    private AppConstants.TAB_TYPE mCurrentTab = AppConstants.TAB_TYPE.TAB_NONE;
    private String mUserLogin;
    private RadioGroup mLayoutFooter;

    @Override
    protected Fragment onCreateMainFragment(Bundle savedInstancesState) {
        mCurrentTab = AppConstants.TAB_TYPE.TAB_RECENT;
        return HomeFragment.newInstance();
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.layoutContent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        mBtnHome = (RadioButton) findViewById(R.id.btnHome);
        mBtnRecent = (RadioButton)findViewById(R.id.btnRecent);
        mBtnGroups = (RadioButton)findViewById(R.id.btnGroups);
        mBtnSettings = (RadioButton)findViewById(R.id.btnSettings);
        mLayoutFooter = (RadioGroup)findViewById(R.id.layoutFooter);

        mBtnHome.setOnClickListener(this);
        mBtnRecent.setOnClickListener(this);
        mBtnGroups.setOnClickListener(this);
        mBtnSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnHome:// TAB HOME
                HomeFragment homeFragment = HomeFragment.newInstance();
                showFragmentWithClearStack(homeFragment);
                mCurrentTab = AppConstants.TAB_TYPE.TAB_HOME;
                break;
            case R.id.btnRecent:// TAB RECENT
                RecentFragment recentFragment = RecentFragment.newInstance();
                showFragmentWithClearStack(recentFragment);
                mCurrentTab = AppConstants.TAB_TYPE.TAB_RECENT;
                break;
            case R.id.btnGroups:// TAB GROUPS
                ContactFragment groupsFragment = ContactFragment.newInstance();
                showFragmentWithClearStack(groupsFragment);
                mCurrentTab = AppConstants.TAB_TYPE.TAB_CONTACT;
                break;
            case R.id.btnSettings:// TAB SETTINGS
                SettingsFragment settingsFragment = SettingsFragment.newIntance();
                showFragmentWithClearStack(settingsFragment);
                mCurrentTab = AppConstants.TAB_TYPE.TAB_SETTINGS;
                break;
        }
    }

    @Override
    public void attachShowChatDetailFr() {
        ChatDetailFragment chatDetailFragment = ChatDetailFragment.newInstance();
        showFragmentWithClearStack(chatDetailFragment);
        mCurrentTab = AppConstants.TAB_TYPE.TAB_CHAT_DETAIL;
        hideFooterLayout();
    }

    @Override
    public void onBackChatDetail() {
        ContactFragment contactFragment = ContactFragment.newInstance();
        showFragmentWithClearStack(contactFragment);
        mCurrentTab = AppConstants.TAB_TYPE.TAB_CONTACT;
        showFooterLayout();
    }

    @Override
    public void hideFooterLayout() {
        mLayoutFooter.setVisibility(View.GONE);
    }

    @Override
    public void showFooterLayout() {
        mLayoutFooter.setVisibility(View.VISIBLE);
    }
}
