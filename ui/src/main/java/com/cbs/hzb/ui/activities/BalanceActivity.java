package com.cbs.hzb.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cbs.bean.ConsumerInfo;
import com.cbs.bill.data.BillList;
import com.cbs.bill.model.AdavancedBill;
import com.cbs.bill.model.SimpleBill;
import com.cbs.hzb.R;
import com.cbs.hzb.ui.adapt.BalanceDataAdapter;
import com.cbs.hzb.ui.adapt.ConsumerInfosAdapter;
import com.cbs.hzb.ui.utils.WrapContentLinearLayoutManager;
import com.cbs.impl.RegisterImpl;
import com.cbs.model.RequestModel;

import org.w3c.dom.Text;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by c on 2017/4/12.
 */

public class BalanceActivity extends BaseActivity{

    private TextView tv_title;
    private TextView tv_holderNum;
    private TextView tv_allSum;
    private TextView tv_averageConsume;
    private RecyclerView rv_balanceData;
    private String billItemId;
    private BalanceDataAdapter balanceDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
    }

    @Override
    protected void findViews() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_holderNum = (TextView) findViewById(R.id.tv_holderNum);
        tv_allSum = (TextView) findViewById(R.id.tv_allSum);
        tv_averageConsume = (TextView) findViewById(R.id.tv_averageConsume);
        rv_balanceData = (RecyclerView) findViewById(R.id.rv_balanceData);
        WrapContentLinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rv_balanceData.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void init() {
        final SimpleBill simpleBill = (SimpleBill) getIntent().getParcelableExtra("SimpleBill");
        billItemId = simpleBill.getId();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SimpleBill simpleBill = BillList.getBillList().getSimpleBillById(billItemId);
        List<ConsumerInfo> consumerInfoList = simpleBill.getConsumerInfos();
        if(consumerInfoList.size() > 0){
            BillList.getBillList().averageBalance(this, BillList.getBillList().getSimpleBillById(billItemId), new BillList.BalanceLisetner() {
                @Override
                public void balanceFinished(AdavancedBill adavancedBill) {
                    if(adavancedBill != null){
                        tv_title.setText(adavancedBill.getTitle());
                        tv_holderNum.setText(String.valueOf(adavancedBill.getHolderNum()));
                        tv_allSum.setText(adavancedBill.getAllSum());
                        tv_averageConsume.setText(adavancedBill.getAverageConsume());
                        balanceDataAdapter = new BalanceDataAdapter(BalanceActivity.this, adavancedBill.getBalanceDataList());
                        rv_balanceData.setAdapter(balanceDataAdapter);
                    }
                }
            });
        }else {
            Toast.makeText(this, "无消费记录，无需计算", Toast.LENGTH_LONG).show();
        }

    }

    public static void show(Context context, SimpleBill simpleBill){
        Bundle bundle = new Bundle();
        bundle.putParcelable("SimpleBill", simpleBill);
        Intent intent = new Intent(context, BalanceActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
