package com.cbs.hzb.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.cbs.hzb.R;
import com.cbs.hzb.ui.adapt.BillAdapter;
import com.cbs.hzb.ui.contracts.BillContracts;
import com.cbs.hzb.ui.presenters.BillPresenterImpl;
import com.cbs.hzb.ui.utils.WrapContentLinearLayoutManager;

import java.util.zip.Inflater;

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
                Toast.makeText(getContext(), String.valueOf(position) + " 被点击", Toast.LENGTH_LONG).show();
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
        Toast.makeText(getContext(), "null", Toast.LENGTH_LONG).show();
    }
}
