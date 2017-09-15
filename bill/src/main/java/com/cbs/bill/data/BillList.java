package com.cbs.bill.data;

import android.content.Context;
import android.widget.Toast;

import com.cbs.bean.BillItem;
import com.cbs.bean.ConsumerInfo;
import com.cbs.bill.model.AdavancedBill;
import com.cbs.bill.model.BalanceData;
import com.cbs.bill.model.SimpleBill;
import com.cbs.common.model.BaseModel;
import com.cbs.common.utils.BillMessageDbUtil;
import com.cbs.common.utils.HelperUtil;
import com.cbs.common.utils.PreferenceHelper.PreferenceConstant;
import com.cbs.common.utils.SharePreferenceUtils;
import com.cbs.domain.ResponeData;
import com.cbs.impl.UpdateBillImpl;
import com.cbs.model.RequestModel;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by yangshaowei on 2017/5/10.
 */

public class BillList extends ArrayList<SimpleBill> {

    //修改，删除，查找账单操作

    private static BillList billList;

    public static BillList getBillList(){
        if(billList == null){
            billList = new BillList();
        }
        return billList;
    }

    public void addSimpleBill(SimpleBill simpleBill){
        add(simpleBill);
    }

    public interface SyncLoadBillLisetner{
        public void loaderFinished();
    }

    public interface BalanceLisetner{
        public void balanceFinished(AdavancedBill adavancedBill);
    }

    public SimpleBill getSimpleBillById(String pid){
        for(SimpleBill simpleBill : billList){
            if(pid.equals(simpleBill.getId())){
                return simpleBill;
            }
        }
        return null;
    }

    /**
     * 网络，数据库，缓存同步
     * @param context
     */
    public void loadBillListFromNet(Context context, final SyncLoadBillLisetner syncLoadBillLisetner){
        final BillMessageDbUtil billMessageDbUtil = new BillMessageDbUtil(context);
        billMessageDbUtil.syncDb(new BillMessageDbUtil.SyncLoaderLisentner(){

            @Override
            public void syncLoaderFinished() {
                //从网络更新数据库
                List<BillItem> billItemList = billMessageDbUtil.queryBillItemAll();

                billList.clear();
                for(BillItem billItem : billItemList){
                    SimpleBill simpleBill  = new SimpleBill();
                    simpleBill.setHoldersId(billItem.getHoldersId());
                    simpleBill.setAllConsume(billItem.getDetail().getAllConsume());
                    simpleBill.setConsumerInfos(billItem.getDetail().getConsumerInfos());
                    simpleBill.setDayConsume(billItem.getDetail().getDayConsume());
                    simpleBill.setMonConsume(billItem.getDetail().getMonConsume());
                    simpleBill.setTitle(billItem.getDetail().getTitle());
                    simpleBill.setBalance(billItem.isBalance());
                    simpleBill.setCreatTime(billItem.getDetail().getCreateTime());
                    simpleBill.setId(billItem.getId());
                    simpleBill.setConsumerInfos(billItem.getDetail().getConsumerInfos());
                    billList.add(simpleBill);
                }

                creatBill();
                syncLoadBillLisetner.loaderFinished();
            }
        });
    }

    /**
     * 数据库与缓存同步
     * @param context
     */
    public void cacheBillList(Context context){
        BillMessageDbUtil billMessageDbUtil = new BillMessageDbUtil(context);
        List<BillItem> billItemList = billMessageDbUtil.queryBillItemAll();

        billList.clear();
        for(BillItem billItem : billItemList){
            SimpleBill simpleBill  = new SimpleBill();
            simpleBill.setHoldersId(billItem.getHoldersId());
            simpleBill.setAllConsume(billItem.getDetail().getAllConsume());
            simpleBill.setConsumerInfos(billItem.getDetail().getConsumerInfos());
            simpleBill.setDayConsume(billItem.getDetail().getDayConsume());
            simpleBill.setMonConsume(billItem.getDetail().getMonConsume());
            simpleBill.setTitle(billItem.getDetail().getTitle());
            simpleBill.setBalance(billItem.isBalance());
            simpleBill.setCreatTime(billItem.getDetail().getCreateTime());
            simpleBill.setId(billItem.getId());
            simpleBill.setConsumerInfos(billItem.getDetail().getConsumerInfos());
            billList.add(simpleBill);
        }
        creatBill();
    }

    /**
     * 创建账单分析数据
     */
    public void creatBill(){
        if(billList != null && billList.size() > 0){
            for(int i=0; i < billList.size(); i++){
                calHolderConsume(billList.get(i));
                calAllSum(billList.get(i));
                calAllDay(billList.get(i));
                calAllMon(billList.get(i));

            }
        }
    }

    /**
     * 计算账单中，每个人的消费总额
     * @param simpleBill
     */
    private void calHolderConsume(SimpleBill simpleBill){
        List<ConsumerInfo> consumerInfos = simpleBill.getConsumerInfos();
        HashMap<String,String> holderConsume = new HashMap<String,String>();

        for(ConsumerInfo consumerInfo : consumerInfos){
            String holder = consumerInfo.getHoldersId();
            String sum = consumerInfo.getSum();
            String alraeadyCal = holderConsume.get(holder);
            if(alraeadyCal != null){
                alraeadyCal = HelperUtil.add(alraeadyCal, sum);
                holderConsume.put(holder, alraeadyCal);
            }else {
                holderConsume.put(holder, sum);
            }
        }
        simpleBill.setHolderConsume(holderConsume);
    }

    /**
     * 计算账单中的消费总额
     * @param simpleBill
     */
    private void calAllSum(SimpleBill simpleBill){
        List<ConsumerInfo> consumerInfos = simpleBill.getConsumerInfos();
        String allSum = null;
        for(ConsumerInfo consumerInfo : consumerInfos){
            String sum = consumerInfo.getSum();
            allSum = HelperUtil.add(allSum, sum);
        }
        simpleBill.setAllConsume(allSum);
    }

    /**
     * 计算账单中的当天消费总额
     * @param simpleBill
     */
    private void calAllDay(SimpleBill simpleBill){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String curTime = formatter.format(curDate);
        List<ConsumerInfo> consumerInfos = simpleBill.getConsumerInfos();
        String allSum = null;
        for(ConsumerInfo consumerInfo : consumerInfos){
            String time = consumerInfo.getTime();
            String sum = consumerInfo.getSum();
            if(time.indexOf(curTime)!=-1){
                allSum = HelperUtil.add(allSum, sum);
            }

        }
        simpleBill.setDayConsume(allSum);
    }

    /**
     * 计算账单中的当月消费总额
     * @param simpleBill
     */
    private void calAllMon(SimpleBill simpleBill){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String curTime = formatter.format(curDate);
        List<ConsumerInfo> consumerInfos = simpleBill.getConsumerInfos();
        String allSum = null;
        for(ConsumerInfo consumerInfo : consumerInfos){
            String time = consumerInfo.getTime();
            String sum = consumerInfo.getSum();
            if(time.indexOf(curTime)!=-1){
                allSum = HelperUtil.add(allSum, sum);
            }

        }
        simpleBill.setMonConsume(allSum);
    }

    /**
     * AA结算
     * @param context
     * @param simpleBill
     * @param balanceLisetner
     */
    public void averageBalance(final Context context, final SimpleBill simpleBill, final BalanceLisetner balanceLisetner){
        BillItem billItem = new BillItem();
        billItem.setBalance(true);
        UpdateBillImpl updateBill = new UpdateBillImpl((String) SharePreferenceUtils.getDBParam(context, PreferenceConstant.LOGIN_USERNAME, null),
                (String) SharePreferenceUtils.getDBParam(context, PreferenceConstant.LOGIN_PASSWORD, null),
                simpleBill.getId(),
                billItem);
        updateBill.request(new RequestModel.RequestCallBack() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                ResponeData responeData = gson.fromJson(s, ResponeData.class);
                if(responeData.isFlag()){
                    //更新数据库
                    BillMessageDbUtil billMessageDbUtil = new BillMessageDbUtil(context);
                    billMessageDbUtil.updateCol(simpleBill.getId(), BaseModel.COLUMN_ISBALANCE, true);

                    //消费账单分析
                    List<BalanceData> balanceDataList = new ArrayList<>();
                    HashMap<String,String> holderConsume = simpleBill.getHolderConsume();
                    AdavancedBill adavancedBill = new AdavancedBill();
                    adavancedBill.setTitle(simpleBill.getTitle());  //账单标题
                    adavancedBill.setAllSum(simpleBill.getAllConsume());  //消费总额
                    adavancedBill.setHolderNum(simpleBill.getHolderConsume().size());  //消费总人数
                    adavancedBill.setAverageConsume(HelperUtil.div(simpleBill.getAllConsume(), String.valueOf(simpleBill.getHolderConsume().size())));  //人均消费

                    Iterator iter = holderConsume.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry entry = (Map.Entry) iter.next();
                        Object key = entry.getKey();
                        Object val = entry.getValue();
                        String sub = HelperUtil.sub(String.valueOf(val),adavancedBill.getAverageConsume());
                        BalanceData balanceData = new BalanceData();
                        balanceData.setHolderId(String.valueOf(key));
                        balanceData.setOut(String.valueOf(val));
                        balanceData.setIn(sub);
                        balanceDataList.add(balanceData);
                    }
                    adavancedBill.setBalanceDataList(balanceDataList);
                    balanceLisetner.balanceFinished(adavancedBill);
                }else {
                    balanceLisetner.balanceFinished(null);
                }
            }
            @Override
            public void onError(Call call, Response response, Exception e) {
                balanceLisetner.balanceFinished(null);
            }
        });
    }
}
