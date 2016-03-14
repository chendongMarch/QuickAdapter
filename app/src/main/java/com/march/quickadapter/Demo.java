package com.march.quickadapter;

import com.march.adapterlibs.QuickInterface;

/**
 * QuickAdapter     com.march.quickadapter
 * Created by 陈栋 on 16/3/14.
 * 功能:
 */
public class Demo implements QuickInterface {
    @Override
    public int getType() {
        if (title.equals("type1")) {
            return 0;
        } else {
            return 1;
        }
//        return 0;
    }

    String title;

    public Demo(String title) {
        this.title = title;
    }
}
