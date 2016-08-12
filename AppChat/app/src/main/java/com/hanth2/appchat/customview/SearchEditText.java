package com.hanth2.appchat.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.hanth2.appchat.R;


@SuppressLint("ClickableViewAccessibility")
public class SearchEditText extends SanProEditText {
    private static final int MAX_LENGTH = 50;
    private TextChangeListener mCallback;

    public SearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public synchronized boolean onEditorAction(TextView v,
                                                       int actionId, KeyEvent event) {
                if (event != null && event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    return true;
                }
                return false;
            }
        });

        String value = "";
        final String viewMode = "editing";
        final String viewSide = "right";
        final Drawable x = getResources().getDrawable(R.drawable.ic_close2);

        // The height will be set the same with [X] icon
//        setHeight(x.getBounds().height());

        x.setBounds(0, 0, x.getIntrinsicWidth(), x.getIntrinsicHeight());
        Drawable x2 = viewMode.equals("never") ? null : viewMode
                .equals("always") ? x : viewMode.equals("editing") ? (value
                .equals("") ? null : x)
                : viewMode.equals("unlessEditing") ? (value.equals("") ? x
                : null) : null;
        final Drawable searchIcon = getResources().getDrawable(
                R.drawable.ic__main_search_white);
        searchIcon.setBounds(0, 0, x.getIntrinsicWidth(),
                x.getIntrinsicHeight());

        setCompoundDrawables(searchIcon, null, viewSide.equals("right") ? x2
                : null, null);

        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (getCompoundDrawables()[viewSide.equals("left") ? 0 : 2] == null) {
                    return false;
                }
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return false;
                }
                // x pressed
                if ((viewSide.equals("left") && event.getX() < getPaddingLeft()
                        + x.getIntrinsicWidth())
                        || (viewSide.equals("right") && event.getX() > getWidth()
                        - getPaddingRight() - x.getIntrinsicWidth())) {
                    Drawable x3 = viewMode.equals("never") ? null : viewMode
                            .equals("always") ? x
                            : viewMode.equals("editing") ? null : viewMode
                            .equals("unlessEditing") ? x : null;
                    setText("");
                    setCompoundDrawables(searchIcon, null,
                            viewSide.equals("right") ? x3 : null, null);
                }
                return false;
            }
        });
        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                Drawable x4 = viewMode.equals("never") ? null : viewMode
                        .equals("always") ? x
                        : viewMode.equals("editing") ? (getText().toString()
                        .equals("") ? null : x) : viewMode
                        .equals("unlessEditing") ? (getText()
                        .toString().equals("") ? x : null) : null;
                setCompoundDrawables(searchIcon, null,
                        viewSide.equals("right") ? x4 : null, null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && s.length() > MAX_LENGTH) {
                    setText(s.subSequence(0, MAX_LENGTH));
                    setSelection(MAX_LENGTH);
                }

                if (mCallback != null) {
                    mCallback.onSearch(s.toString());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
        });
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F);
    }

    public void setOnTextChangeListener(TextChangeListener callback) {
        this.mCallback = callback;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            dispatchKeyEvent(event);
            return false;
        }
        return super.onKeyPreIme(keyCode, event);
    }

    public interface TextChangeListener {
        void onTextChange(CharSequence s);

        void onSearch(String s);

        void onTouch();

        void onBackKey();
    }
}
