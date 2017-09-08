package com.cbs.common.utils;

import android.content.Context;

import com.cbs.bean.ConsumerInfo;
import com.cbs.common.model.ConsumerInfos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangshaowei on 2017/9/5.
 */

public class HelperUtil {
    private Context mContext;
    public HelperUtil(Context context){
        mContext = context;
    }

    public List<ConsumerInfo> toConsumerInfo(List<ConsumerInfos> consumerInfosList){
        List<ConsumerInfo> consumerInfoList = new ArrayList<ConsumerInfo>();
        for(ConsumerInfos consumerInfos : consumerInfosList){
            ConsumerInfo consumerInfo = new ConsumerInfo();
            consumerInfo.setCid(consumerInfos.getCid());
            consumerInfo.setTime(consumerInfos.getTime());
            consumerInfo.setSum(consumerInfos.getSum());
            consumerInfo.setHoldersId(consumerInfos.getHoldersId());
            consumerInfo.setDescribe(consumerInfos.getDescribe());
            consumerInfo.setId(String.valueOf(consumerInfos.getId()));
            consumerInfo.setType(consumerInfos.getType());
            consumerInfoList.add(consumerInfo);
        }
        return consumerInfoList;
    }
}
