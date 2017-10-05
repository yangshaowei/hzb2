package com.cbs.hzb.ui.contracts;

import android.app.Activity;

/**
 * Created by yangshaowei on 2017/3/21.
 */

public interface ShareAppContract {

    interface Presenter extends BasePresenter {
        void share2QQ(Activity activity, String shareUrl);

        void share2WX();

        String getShareMsg();
        String getWXShareMsg();
    }
}
