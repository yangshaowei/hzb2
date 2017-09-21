package com.cbs.hzb.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cbs.hzb.R;

/**
 * Created by yangshaowei on 17/5/24.
 */

public class TwoButtontContentDialog extends Dialog {

    private EditText tv_content;
    private Button bt_button1;
    private Button bt_button2;

    public TwoButtontContentDialog(Context context, String button1, String button2) {
        super(context, R.style.contact_delect_style);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_twobutton_content, null);
        tv_content = (EditText) view.findViewById(R.id.tv_content);
        bt_button1 = (Button) view.findViewById(R.id.bt_button1);
        bt_button2 = (Button) view.findViewById(R.id.bt_button2);
        bt_button1.setText(button1);
        bt_button2.setText(button2);
        setCancelable(true);
        setContentView(view);
    }

    public void setOnButton1Listener(View.OnClickListener listener) {
        bt_button1.setOnClickListener(listener);
    }

    public void setOnButton2Listener(View.OnClickListener listener) {
        bt_button2.setOnClickListener(listener);
    }

    public EditText getEditext(){
        return tv_content;
    }
}
