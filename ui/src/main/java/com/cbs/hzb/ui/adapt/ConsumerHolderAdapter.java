package com.cbs.hzb.ui.adapt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cbs.bean.ConsumerInfo;
import com.cbs.hzb.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by yangshaowei on 2017/4/3.
 */

public class ConsumerHolderAdapter extends RecyclerView.Adapter<ConsumerHolderAdapter.ViewHolder> {

    private Context mContext;

    private List<String[]> mDataList = new ArrayList<>();

    private OnItemClickListener mOnItemClickListener;

    public ConsumerHolderAdapter(Context context, HashMap<String,String> list) {
        mContext = context;
        sort(list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_holder_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final String[] value = mDataList.get(position);
        holder.tv_name.setText(value[0]);
        holder.tv_sum.setText(value[1]);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setDataList(HashMap<String,String> list){
        sort(list);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_name;
        TextView tv_sum;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);  //条目点击事件
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
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

    /**
     * 排序
     * @param list
     */
    private void sort(HashMap<String,String> list) {
        mDataList.clear();
        ArrayList<Map.Entry<String,String>> entries= sortMap(list);
        for(Map.Entry<String,String> entry : entries){
            Object key = entry.getKey();
            Object val = entry.getValue();
            mDataList.add(new String[]{(String)key,(String)val});
        }
    }

    public static ArrayList<Map.Entry<String,String>> sortMap(Map map){
        List<Map.Entry<String, String>> entries = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> obj1 , Map.Entry<String, String> obj2) {
                return (int) (Double.valueOf(obj2.getValue()) - Double.valueOf(obj1.getValue()));
            }
        });
        return (ArrayList<Map.Entry<String, String>>) entries;
    }
}
