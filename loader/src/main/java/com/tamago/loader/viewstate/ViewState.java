package com.tamago.loader.viewstate;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.tamago.loader.GreyDrawable;

/**
 * Created by osamaaftab on 9/12/18.
 */
public abstract class ViewState<V extends View> {
    V view;
    Drawable background;
    protected boolean darker;

    public ViewState(V view) {
        this.view = view;
    }

    protected void restore() {
    }

    protected void restoreBackground() {
        this.view.setBackgroundDrawable(background);
    }

    public void beforeStart(){
        this.background = view.getBackground();
    }

    public void start(boolean fadein,boolean roundCorner,int radius) {
        GreyDrawable greyDrawable = new GreyDrawable();
        this.view.setBackgroundDrawable(greyDrawable);
        greyDrawable.setFadein(fadein);
        greyDrawable.setRadius(radius);
        greyDrawable.setRoundCorners(roundCorner);
        greyDrawable.start(view, darker);
    }

    public void stop() {
        Drawable drawable = this.view.getBackground();
        if(drawable instanceof GreyDrawable){
            GreyDrawable greyDrawable = (GreyDrawable)drawable;
            greyDrawable.stop(new GreyDrawable.Callback(){
                @Override
                public void onFadeOutStarted() {
                    restore();
                }

                @Override
                public void onFadeOutFinished() {
                    restoreBackground();
                }
            });
        } else {
            restore();
        }
    }
}
