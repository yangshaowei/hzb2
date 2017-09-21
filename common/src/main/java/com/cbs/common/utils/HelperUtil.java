package com.cbs.common.utils;

import android.content.Context;

import com.cbs.bean.ConsumerInfo;
import com.cbs.common.model.ConsumerInfos;

import java.math.BigDecimal;
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

    public static String add(String... nums){
        BigDecimal sum = new BigDecimal("0");
        for(String num : nums){
            if(num != null){
                BigDecimal bignum = new BigDecimal(num);
                sum =  sum.add(bignum);
            }
        }
        return sum.toString();
    }

    public static String sub(String numSub, String num){
        BigDecimal bignumSub = new BigDecimal(numSub);
        BigDecimal bignum = new BigDecimal(num);
        BigDecimal bignumRult = null;
        bignumRult = bignumSub.subtract(bignum);
        return bignumRult.toString();
    }

    public static String mul(String numMul, String num){
        BigDecimal bignumMul = new BigDecimal(numMul);
        BigDecimal bignum = new BigDecimal(num);
        BigDecimal bignumRult = null;
        bignumRult = bignumMul.multiply(bignum);
        return bignumRult.toString();
    }

    public static String div(String numDiv, String num){
        if(numDiv != null && !"".equals(numDiv) &&
                num != null && !"".equals(num)){
            BigDecimal bignumDiv = new BigDecimal(numDiv);
            BigDecimal bignum = new BigDecimal(num);
            BigDecimal bignumRult = null;
            bignumRult = bignumDiv.divide(bignum, 2, BigDecimal.ROUND_HALF_DOWN);
            return bignumRult.toString();
        }
        return null;
    }
}
