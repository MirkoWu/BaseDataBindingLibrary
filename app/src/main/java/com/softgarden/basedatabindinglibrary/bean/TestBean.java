package com.softgarden.basedatabindinglibrary.bean;

import android.databinding.BaseObservable;

/**
 * Created by DELL on 2017/8/10.
 */

public class TestBean extends BaseObservable{
    private String name;

    public TestBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
