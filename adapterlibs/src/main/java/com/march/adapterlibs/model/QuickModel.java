package com.march.adapterlibs.model;

import com.march.adapterlibs.QuickInterface;

/**
 * QuickAdapter     com.march.adapterlibs.bean
 * Created by 陈栋 on 16/4/8.
 * 功能:
 */
public class QuickModel implements QuickInterface {

    private Object t;

    public QuickModel(Object t) {
        this.t = t;
    }

    public <T> T get() {
        return (T) t;
    }

    public void put(Object t) {
        this.t = t;
    }

    @Override
    public int getType() {
        return 0;
    }
}
