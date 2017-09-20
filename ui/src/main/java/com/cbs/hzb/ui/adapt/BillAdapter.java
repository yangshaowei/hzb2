package com.cbs.hzb.ui.adapt;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cbs.bill.data.BillList;
import com.cbs.bill.model.SimpleBill;
import com.cbs.hzb.R;
import com.cbs.hzb.ui.activities.BalanceActivity;
import com.cbs.hzb.ui.dialogs.TwoImageDialog;
import com.cbs.hzb.ui.presenters.ShareAppPresenter;


/**
 * Created by yangshaowei on 2017/4/3.
 */

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {

    private Activity mActivity;

    private BillList mList;

    private OnItemClickListener mOnItemClickListener;

    public BillAdapter(Activity activity, BillList list) {
        mActivity = activity;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final SimpleBill simpleBill = mList.get(position);
        holder.tv_id.setText(simpleBill.getId());
        holder.tv_time.setText(simpleBill.getCreatTime());
        holder.tv_holdersId.setText(simpleBill.getHoldersId());
        holder.tv_title.setText(simpleBill.getTitle());
        if(simpleBill.isBalance){
            holder.tv_balance.setText("已结算");
        }else {
            holder.tv_balance.setText("未结算");
        }
        holder.tv_delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "订单号 : " + simpleBill.getId() + " 被删除", Toast.LENGTH_LONG).show();
            }
        });

        holder.tv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TwoImageDialog twoImageDialog = new TwoImageDialog(mActivity);
                twoImageDialog.setOnQQListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShareAppPresenter shareAppPresenter = new ShareAppPresenter(mActivity);
                        shareAppPresenter.share2QQ(mActivity);
                    }
                });
                twoImageDialog.setOnWechatListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShareAppPresenter shareAppPresenter = new ShareAppPresenter(mActivity);
                        shareAppPresenter.share2WX();
                    }
                });
                twoImageDialog.show();
            }
        });

        holder.tv_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BalanceActivity.show(mActivity, simpleBill);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public SimpleBill getItem(int position) {
        return mList.get(position);
    }

    public void setDataList(BillList contacts){
        mList = contacts;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_id;
        TextView tv_time;
        TextView tv_holdersId;
        TextView tv_balance;
        TextView tv_share;
        TextView tv_delect;
        TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);  //条目点击事件
            tv_id = (TextView) itemView.findViewById(R.id.tv_id);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_holdersId = (TextView) itemView.findViewById(R.id.tv_holdersid);
            tv_balance = (TextView) itemView.findViewById(R.id.tv_balance);
            tv_share = (TextView) itemView.findViewById(R.id.tv_share);
            tv_delect = (TextView) itemView.findViewById(R.id.tv_delect);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
