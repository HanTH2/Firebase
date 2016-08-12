package com.hanth2.appchat.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

@SuppressLint("Recycle")
public class SanProEditText extends EditText {

    public SanProEditText(Context context) {
        super(context);
    }

    public SanProEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            initialize(context, attrs);
        }
    }

    public SanProEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void initialize(Context context, AttributeSet attrs) {
        int[] attributes = new int[]{
                android.R.attr.textStyle
        };
        TypedArray arr = context.obtainStyledAttributes(attrs, attributes);
        int fonttype = arr.getInt(0, Typeface.NORMAL);
        arr.recycle();

        switch (fonttype) {
            case Typeface.BOLD:
                Typeface font = Typeface.createFromAsset(getContext().getAssets(),
                        "font/SourceSansPro-Bold.otf");
                setTypeface(font, Typeface.BOLD);
                break;
            case Typeface.ITALIC:
                font = Typeface.createFromAsset(getContext().getAssets(),
                        "font/SourceSansPro-BoldIt.otf");
                setTypeface(font, Typeface.ITALIC);
                break;
            default:
                font = Typeface.createFromAsset(getContext().getAssets(),
                        "font/SourceSansPro-Regular.otf");
                setTypeface(font, Typeface.NORMAL);
                break;
        }
    }
}

