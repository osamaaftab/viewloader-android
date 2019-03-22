package com.tamago.loader;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tamago.loader.viewstate.ImageViewState;
import com.tamago.loader.viewstate.TextViewState;
import com.tamago.loader.viewstate.ViewState;

import java.util.HashMap;

/**
 * Created by osamaaftab on 9/12/18.
 */

public class ViewLoader {
    private final Context context;

    private HashMap<View, ViewState> viewsState;

    boolean fadein = true;

    boolean round = false;

    int radius = 0;

    private boolean started;

    public ViewLoader(Context context) {
        this.context = context;
        this.viewsState = new HashMap<>();
    }

    public static ViewLoader with(Context context) {
        return new ViewLoader(context);
    }

    public ViewLoader on(int... viewsId) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            for (int view : viewsId) {
                add(activity.findViewById(view));
            }
        }
        return this;
    }

    public ViewLoader fadein(boolean fadein) {
        this.fadein = fadein;
        return this;
    }

    public ViewLoader roundCorners(boolean round) {
        this.round = round;
        return this;
    }

    public ViewLoader setCorners(int radius) {
        this.radius = radius;
        return this;
    }

    private void add(View view) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            viewsState.put(view, new TextViewState(textView));
        } else if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            viewsState.put(view, new ImageViewState(imageView));
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; ++i) {
                View child = viewGroup.getChildAt(i);
                add(child);
            }
        }
    }

    public ViewLoader on(View... views) {
        for (View view : views)
            add(view);
        return this;
    }

    public ViewLoader except(View... views) {
        for (View view : views) {
            this.viewsState.remove(view);
        }
        return this;
    }

    public ViewLoader start() {
        if (!started) {
            //prepare for starting
            for (ViewState viewState : viewsState.values()) {
                viewState.beforeStart();
            }
            started = true;
            //start
            for (ViewState viewState : viewsState.values()) {
                viewState.start(fadein, round,radius);
            }
        }
        return this;
    }

    public ViewLoader stop() {
        if (started) {
            for (ViewState viewState : viewsState.values()) {
                viewState.stop();
            }
            started = false;
        }
        return this;
    }
}