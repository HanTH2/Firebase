package com.hanth2.appchat.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import java.util.Stack;

/**
 * Created by HanTH2 on 8/12/2016.
 */
abstract public class BaseFragmentActivity extends Activity implements FragmentManager.OnBackStackChangedListener {
    protected final Stack<String> mFragmentTagStack = new Stack<>();

    abstract protected Fragment onCreateMainFragment(Bundle savedInstancesState);

    abstract protected int getFragmentContainerId();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        showFragment(onCreateMainFragment(null));
        getFragmentManager().addOnBackStackChangedListener(this);
    }

    public void showFragmentWithClearStack(Fragment f)
    {
        getFragmentManager().popBackStack(null, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        mFragmentTagStack.clear();
        showFragment(f);
    }

    public void showFragment(Fragment f)
    {
        String tag = f.getClass().getName() + getNextPositionOfFragment(f.getClass().getName());
        final android.app.FragmentManager fm = getFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        if (mFragmentTagStack.size() > 0)
        {
            final Fragment ff = fm.findFragmentByTag(mFragmentTagStack.peek());
            ft.hide(ff);
        }
        ft.add(getFragmentContainerId(), f, tag);
        ft.show(f);
        ft.addToBackStack(tag);
        ft.commit();
        mFragmentTagStack.add(tag);
    }

    @Override
    public void onBackStackChanged()
    {
        android.app.FragmentManager fm = getFragmentManager();

        if (fm.getBackStackEntryCount() == mFragmentTagStack.size())
        {
            return;
        }

        if (mFragmentTagStack.size() > 1)
        {
            FragmentTransaction ft = fm.beginTransaction();
            String tag = mFragmentTagStack.pop();
            if (fm.findFragmentByTag(tag) != null)
            {
                ft.remove(fm.findFragmentByTag(tag));
            }
            ft.commit();
        }
    }

    private int getNextPositionOfFragment(String tag)
    {
        int pos = 0;
        if (mFragmentTagStack.size() > 0)
        {
            for (String stackTag : mFragmentTagStack)
            {
                if (stackTag.contains(tag))
                {
                    pos++;
                }
            }
        }
        return pos;
    }
}
