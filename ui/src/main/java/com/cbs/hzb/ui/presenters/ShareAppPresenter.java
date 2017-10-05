package com.cbs.hzb.ui.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.cbs.common.utils.CommonUtils;
import com.cbs.common.utils.PreferenceHelper.PreferenceConstant;
import com.cbs.common.utils.SharePreferenceUtils;
import com.cbs.hzb.R;
import com.cbs.hzb.ui.activities.AddConsumerInfosActivity;
import com.cbs.hzb.ui.contracts.ShareAppContract;
import com.cbs.hzb.ui.utils.Config;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * Created by yangshaowei on 2017/3/21.
 */

public class ShareAppPresenter implements ShareAppContract.Presenter {
    private Context context;
    IWXAPI api;

    @Override
    public void start() {
//        String versionName = context.getString(R.string.version_name, CommonUtils.getVersionName(context));
        api = WXAPIFactory.createWXAPI(context, Config.WX_APPID, false);
        api.registerApp(Config.WX_APPID);
    }

    public ShareAppPresenter(Context context) {
        this.context = context;
        start();
    }

    @Override
    public void share2QQ(Activity activity, String shareUrl) {
        boolean isInstall = CommonUtils.checkApkExist(context, "com.tencent.mobileqq");
        boolean isInstallLite = CommonUtils.checkApkExist(context, "com.tencent.qqlite");
        if (!isInstall && !isInstallLite) {
            Toast.makeText(context, "未安装QQ", Toast.LENGTH_LONG).show();
            return;
        }
        if (!CommonUtils.isNetworkConnected(context)) {
            Toast.makeText(context, "网络不可用，请检查网络设置。", Toast.LENGTH_LONG).show();
            return;
        }
        Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, context.getString(R.string.qq_title));
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, getWXShareMsg());
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, shareUrl);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, context.getString(R.string.qq_image_url));
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, context.getString(R.string.qq_app_name));
        Tencent tencent = Tencent.createInstance(Config.QQ_APPID, context);
        tencent.shareToQQ(activity, params, new IUiListener() {
            @Override
            public void onError(UiError e) {
                Toast.makeText(context, "onError  code:" + e.errorCode + ", msg:" + e.errorMessage + ", detail:" + e.errorDetail, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onComplete(Object arg0) {
                Toast.makeText(context, "发送成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(context, "发送取消", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void share2WX() {
        if (TextUtils.isEmpty(Config.WX_APPID)) {
            Toast.makeText(context, context.getString(R.string.wx_appid_invalid), Toast.LENGTH_LONG).show();
        }
        if (api == null) {
            return;
        }
        if (!api.isWXAppInstalled()) {
            Toast.makeText(context, context.getString(R.string.wx_unistall), Toast.LENGTH_LONG).show();
            return;
        }
        if (!CommonUtils.isNetworkConnected(context)) {
            Toast.makeText(context, "网络不可用，请检查网络设置。", Toast.LENGTH_LONG).show();
            return;
        }

        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = context.getString(R.string.share_app_msg_url);

//        String text = getWXShareMsg();
        // 初始化一个WXTextObject对象
//        WXTextObject textObj = new WXTextObject();
//        textObj.text = text;

        // 用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage(webpage);
//        msg.mediaObject = textObj;
        // 发送文本类型的消息时，title字段不起作用
        msg.title = context.getString(R.string.app_name);
        msg.description = getWXShareMsg();

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "text" + System.currentTimeMillis();
        ; // transaction字段用于唯一标识一个请求
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        // 调用api接口发送数据到微信
        api.sendReq(req);
    }

    @Override
    public String getShareMsg() {

        String name = (String) SharePreferenceUtils.getDBParam(context, PreferenceConstant.LOGIN_USERNAME, null);
        return name + context.getString(R.string.share_app_msg_text) + "：" + context.getString(R.string.share_app_msg_url);
    }

    @Override
    public String getWXShareMsg() {
        String name = (String) SharePreferenceUtils.getDBParam(context, PreferenceConstant.LOGIN_USERNAME, null);
        return name + context.getString(R.string.share_app_msg_text);
    }

}
