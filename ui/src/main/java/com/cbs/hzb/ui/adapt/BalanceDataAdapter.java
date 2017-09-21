package com.cbs.hzb.ui.adapt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cbs.bean.ConsumerInfo;
import com.cbs.bill.model.BalanceData;
import com.cbs.hzb.R;

import java.util.List;


/**
 * Created by yangshaowei on 2017/4/3.
 */

public class BalanceDataAdapter extends RecyclerView.Adapter<BalanceDataAdapter.ViewHolder> {

    private Context mContext;

    private List<BalanceData> mList;

    private OnItemClickListener mOnItemClickListener;

    public BalanceDataAdapter(Context context, List<BalanceData>  list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_balance_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final BalanceData balanceData = mList.get(position);
        holder.tv_name.setText(balanceData.getHolderId());
        holder.tv_out.setText(balanceData.getOut());
        holder.tv_in.setText(balanceData.getIn());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public BalanceData getItem(int position) {
        return mList.get(position);
    }

    public void setDataList(List<BalanceData> list){
        mList = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_name;
        TextView tv_out;
        TextView tv_in;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);  //条目点击事件
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_out = (TextView) itemView.findViewById(R.id.tv_out);
            tv_in = (TextView) itemView.findViewById(R.id.tv_in);
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
