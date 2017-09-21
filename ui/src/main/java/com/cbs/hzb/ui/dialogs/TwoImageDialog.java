package com.cbs.hzb.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cbs.hzb.R;

/**
 * Created by yangshaowei on 17/5/24.
 */

public class TwoImageDialog extends Dialog {

    private LinearLayout ll_QQ;
    private LinearLayout ll_wechat;

    public TwoImageDialog(Context context) {
        super(context, R.style.contact_delect_style);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_twoimage, null);
        ll_QQ = (LinearLayout) view.findViewById(R.id.ll_QQ);
        ll_wechat = (LinearLayout) view.findViewById(R.id.ll_wechat);
        setCancelable(true);
        setContentView(view);
    }

    public void setOnQQListener(View.OnClickListener listener) {
        ll_QQ.setOnClickListener(listener);
    }

    public void setOnWechatListener(View.OnClickListener listener) {
        ll_wechat.setOnClickListener(listener);
    }

}
