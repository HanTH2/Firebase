package com.hanth2.appchat.base;

import android.app.Activity;
import android.app.Fragment;

/**
 * Created by HanTH2 on 8/12/2016.
 */
abstract public class BaseFragment extends Fragment {
    protected void signOutFirebase(){
        ((BaseActivity)getActivity()).signOutFirebase();
    }
}
