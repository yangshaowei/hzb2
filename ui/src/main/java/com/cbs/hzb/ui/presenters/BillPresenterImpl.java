package com.cbs.hzb.ui.presenters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.cbs.Constant.AppConstant;
import com.cbs.bill.data.BillList;
import com.cbs.bill.model.SimpleBill;
import com.cbs.common.utils.BillMessageDbUtil;
import com.cbs.common.utils.PreferenceHelper.PreferenceConstant;
import com.cbs.common.utils.SharePreferenceUtils;
import com.cbs.domain.ResponeData;
import com.cbs.hzb.ui.activities.AddConsumerInfosActivity;
import com.cbs.hzb.ui.adapt.BillAdapter;
import com.cbs.hzb.ui.contracts.BillContracts;
import com.cbs.hzb.ui.dialogs.TwoImageDialog;
import com.cbs.impl.GetSignImpl;
import com.cbs.model.RequestModel;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by yangshaowei on 2017/5/10.
 */

public class BillPresenterImpl implements BillContracts.Presenter{

    @Override
    public void itemClick(Context context, SimpleBill simpleBill) {

    }

    @Override
    public BillList getBillList() {
        return BillList.getBillList();
    }

    @Override
    public void notifyDataChange(Context context, BillAdapter adapter) {
        BillList.getBillList().cacheBillList(context);
        adapter.notifyDataSetChanged();
    }

    /**
     * 加载缓存
     */
    public void startCacheLoading(Context context, BillList.SyncLoadBillLisetner syncLoadBillLisetner) {
        BillList.getBillList().loadBillListFromNet(context, syncLoadBillLisetner);
    }

    /**
     * 获取分享链接
     */
    @Override
    public void getInviteLink(final Activity activity, final String billId){
        String curTime = getCurrentTime();
        GetSignImpl getSign = new GetSignImpl((String) SharePreferenceUtils.getDBParam(activity, PreferenceConstant.LOGIN_USERNAME, null),
                (String) SharePreferenceUtils.getDBParam(activity, PreferenceConstant.LOGIN_PASSWORD, null),
                billId,
                curTime);
        getSign.request(new RequestModel.RequestCallBack() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                final ResponeData responeData = gson.fromJson(s, ResponeData.class);
                if(responeData.isFlag()){
                    TwoImageDialog twoImageDialog = new TwoImageDialog(activity);
                    twoImageDialog.setOnQQListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url;
                            url = AppConstant.InviteUrl+"?"+"billId="+billId+"&timestamp="+responeData.getData().getTimestamp()+
                                    "&sign="+responeData.getData().getSign();
                            ShareAppPresenter shareAppPresenter = new ShareAppPresenter(activity);
                            shareAppPresenter.share2QQ(activity, url);
                        }
                    });
                    twoImageDialog.setOnWechatListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ShareAppPresenter shareAppPresenter = new ShareAppPresenter(activity);
                            shareAppPresenter.share2WX();
                        }
                    });
                    twoImageDialog.show();
                }else {

                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {

            }
        });
    }

    /**
     * 获取10位的时间戳
     * @return
     */
    private String getCurrentTime(){
        long time=System.currentTimeMillis()/1000;//获取系统时间的10位的时间戳
        String  str=String.valueOf(time);
        return str;
    }


}
