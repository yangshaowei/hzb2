package com.cbs.hzb.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cbs.hzb.R;

import java.util.zip.Inflater;

/**
 * Created by c on 2017/4/12.
 */

public class BillFragment extends BaseFragment implements View.OnClickListener{

    Button bt_null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void initData() {
        bt_null = (Button) getActivity().findViewById(R.id.bt_null);
        bt_null.setOnClickListener(this);
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
