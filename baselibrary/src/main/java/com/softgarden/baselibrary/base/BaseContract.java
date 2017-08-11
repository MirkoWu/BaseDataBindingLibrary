package com.softgarden.baselibrary.base;

/**
 * Created by DELL on 2017/8/11.
 */

public class BaseContract {
    public interface Display extends IBaseDisplay {

    }

    public interface Presenter extends IBasePresenter<Display> {
    }
}
