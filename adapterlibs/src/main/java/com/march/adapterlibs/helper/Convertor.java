package com.march.adapterlibs.helper;

import com.march.adapterlibs.model.QuickModel;

import java.util.ArrayList;
import java.util.List;

/**
 * QuickAdapter     com.march.adapterlibs.helper
 * Created by 陈栋 on 16/4/8.
 * 功能:
 */
public class Convertor {

    public static <T> List<QuickModel> convert(List<T> list){
        List<QuickModel> quicks = new ArrayList<>();
        for (T t  : list) {
            quicks.add(new QuickModel(t));
        }
        return quicks;
    }


    public static <T> List<QuickModel> convert(T[] list){
        List<QuickModel> quicks = new ArrayList<>();
        for (T t  : list) {
            quicks.add(new QuickModel(t));
        }
        return quicks;
    }

}
