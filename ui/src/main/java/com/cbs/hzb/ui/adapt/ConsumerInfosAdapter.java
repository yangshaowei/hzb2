package com.cbs.hzb.ui.adapt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cbs.bean.ConsumerInfo;
import com.cbs.bill.data.BillList;
import com.cbs.bill.model.SimpleBill;
import com.cbs.hzb.R;

import java.util.List;


/**
 * Created by yangshaowei on 2017/4/3.
 */

public class ConsumerInfosAdapter extends RecyclerView.Adapter<ConsumerInfosAdapter.ViewHolder> {

    private Context mContext;

    private List<ConsumerInfo> mList;

    private OnItemClickListener mOnItemClickListener;

    public ConsumerInfosAdapter(Context context, List<ConsumerInfo> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consumerinfos_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ConsumerInfo consumerInfo = mList.get(position);
        holder.tv_cid.setText(consumerInfo.getCid());
        holder.tv_time.setText(consumerInfo.getTime());
        holder.tv_holdersId.setText(consumerInfo.getHoldersId());
        holder.tv_des.setText(consumerInfo.getDescribe());
        holder.tv_sum.setText(consumerInfo.getSum());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public ConsumerInfo getItem(int position) {
        return mList.get(position);
    }

    public void setDataList(List<ConsumerInfo> list){
        mList = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_cid;
        TextView tv_time;
        TextView tv_holdersId;
        TextView tv_des;
        TextView tv_sum;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);  //条目点击事件
            tv_cid = (TextView) itemView.findViewById(R.id.tv_cid);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_holdersId = (TextView) itemView.findViewById(R.id.tv_holdersid);
            tv_des = (TextView) itemView.findViewById(R.id.tv_des);
            tv_sum = (TextView) itemView.findViewById(R.id.tv_sum);
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
