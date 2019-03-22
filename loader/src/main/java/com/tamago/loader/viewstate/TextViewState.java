package com.tamago.loader.viewstate;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.widget.TextView;

/**
 * Created by osamaaftab on 9/12/18.
 */
public class TextViewState extends ViewState<TextView> {
    ColorStateList textColor;

    public TextViewState(TextView textView) {
        super(textView);
    }

    @Override
    public void beforeStart() {
        super.beforeStart();
        this.textColor = view.getTextColors();
        this.darker = view.getTypeface() != null && view.getTypeface().isBold();
    }

    @Override
    protected void restore() {
        this.view.setTextColor(textColor);
    }

    @Override
    public void start(boolean fadein, boolean corners, int radius) {
        super.start(fadein, corners, radius);
        view.setTextColor(Color.TRANSPARENT);
    }
}
