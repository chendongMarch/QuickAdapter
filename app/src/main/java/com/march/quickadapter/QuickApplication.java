package com.march.quickadapter;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.march.adapterlibs.quick.Quick;

/**
 * QuickAdapter     com.march.quickadapter
 * Created by 陈栋 on 16/3/14.
 * 功能:
 */
public class QuickApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Quick.init(new Quick.QuickLoad() {
            @Override
            public void load(Context context, String url, ImageView view) {
                Log.e("chendong","加载图片");
                Glide.with(context).load("http://www.fresco-cn.org/static/fresco-logo.png").into(view);
            }
        });
    }
}
