package com.march.adapterlibs.quick;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.march.adapterlibs.R;

/**
 * QuickAdapter     com.march.adapterlibs.quick
 * Created by 陈栋 on 16/3/14.
 * 功能:
 */
public class Quick {
    public static QuickLoad imgLoadTool;

    public static void init(QuickLoad tool) {
        imgLoadTool = tool;
    }

    public static QuickLoad get() {
        if (imgLoadTool == null) {
            throw new IllegalStateException("if u want to load online img,u must invoke Quick.init() at the first!");
        }
        return imgLoadTool;
    }

    public static <T> T getTagOutOfAdapter(View view){
        return (T) view.getTag(R.string.quick_key);
    }
    public interface QuickLoad {
        void load(Context context, String url, ImageView view);
    }
}
