package com.cbs.hzb.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.cbs.bean.BillItem;
import com.cbs.bean.Data;
import com.cbs.bean.Detail;
import com.cbs.bill.model.SimpleBill;
import com.cbs.common.utils.BillMessageDbUtil;
import com.cbs.common.utils.PreferenceHelper.PreferenceConstant;
import com.cbs.common.utils.SharePreferenceUtils;
import com.cbs.domain.ResponeData;
import com.cbs.hzb.R;
import com.cbs.hzb.ui.activities.ConsumerInfosDetailActivity;
import com.cbs.hzb.ui.adapt.BillAdapter;
import com.cbs.hzb.ui.contracts.BillContracts;
import com.cbs.hzb.ui.dialogs.TwoButtontContentDialog;
import com.cbs.hzb.ui.presenters.BillPresenterImpl;
import com.cbs.hzb.ui.utils.WrapContentLinearLayoutManager;
import com.cbs.impl.CreatBillImpl;
import com.cbs.model.RequestModel;
import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by yangshaowei on 2017/4/12.
 */

public class BillFragment extends BaseFragment implements View.OnClickListener{

    Button bt_creat;
    RecyclerView recyclerView;
    BillAdapter billAdapter;
    BillContracts.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressLint("NewApi")
    @Override
    public void initData() {
        bt_creat = (Button) getActivity().findViewById(R.id.bt_creat);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.bill_list);
        bt_creat.setOnClickListener(this);
        presenter = new BillPresenterImpl();
        WrapContentLinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        billAdapter = new BillAdapter(getContext(), presenter.getBillList());
        billAdapter.setOnItemClickListener(new BillAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转详细信息
                SimpleBill simpleBill = billAdapter.getItem(position);
                ConsumerInfosDetailActivity.show(getContext(), simpleBill);
            }
        });
        recyclerView.setAdapter(billAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_bill, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;

        }
        return false;
    }

    @Override
    public void onClick(View v) {
        final TwoButtontContentDialog twoButtontContentDialog = new TwoButtontContentDialog(getContext(), "取消", "确定");
        twoButtontContentDialog.setOnButton1Listener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twoButtontContentDialog.dismiss();
            }
        });
        twoButtontContentDialog.setOnButton2Listener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twoButtontContentDialog.dismiss();
                BillItem billItem = new BillItem();
                Detail detail = new Detail();
                detail.setTitle(twoButtontContentDialog.getEditext().getText().toString());
                billItem.setDetail(detail);
                billItem.setHoldersId((String) SharePreferenceUtils.getDBParam(getContext(), PreferenceConstant.LOGIN_USERNAME, null));
                CreatBillImpl creatBill = new CreatBillImpl((String) SharePreferenceUtils.getDBParam(getContext(), PreferenceConstant.LOGIN_USERNAME, null),
                                                            (String) SharePreferenceUtils.getDBParam(getContext(), PreferenceConstant.LOGIN_PASSWORD, null),
                                                            billItem);
                creatBill.request(new RequestModel.RequestCallBack() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ResponeData responeData = gson.fromJson(s, ResponeData.class);
                        Data data = responeData.getData();
                        BillMessageDbUtil billMessageDbUtil = new BillMessageDbUtil(getContext());
                        billMessageDbUtil.insert(data.getBillItem());
                        presenter.notifyDataChange(getContext(), billAdapter);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {

                    }
                });

            }
        });
        twoButtontContentDialog.show();
    }
}
