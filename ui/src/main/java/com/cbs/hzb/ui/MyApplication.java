package com.cbs.hzb.ui;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;

import com.cbs.bill.data.BillList;
import com.cbs.hzb.R;
import com.cbs.okserver.download.DownloadService;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.model.HttpHeaders;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


import java.io.File;


/**
 * Created by yangshaowei on 17/4/20.
 */
public class MyApplication extends Application {

    private static DisplayImageOptions displayImageOptions;
    private static String channel;//渠道
    protected static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        initImageLoader();
        initOkHttpUtils();
        setDownLoadPath();//在okgo初始化之后

//        channel=getApplicationMetaValue("UMENG_CHANNEL");

    }

    private void initImageLoader(){
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);

        displayImageOptions=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.drawable.contacts_search_normal)
                .showImageForEmptyUri(R.drawable.contacts_search_pressed)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    public static DisplayImageOptions getDisplayOptions(){
        return displayImageOptions;
    }

    private void initOkHttpUtils(){

        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        HttpHeaders headers = new HttpHeaders(); //header不支持中文
        //TMMHelper1.1.0.001.01_Android
//        headers.put("appname", AppConstant.AppVersion);//客户端名称
//        headers.put("channel-id", "5410231609");//注册渠道号
//        headers.put("md", DefaultHttpHeader.getMMDeviceInfo(this));//用户设备信息=IMSI+IMEI+MAC+IP
//        headers.put("mi","63835ec5-1cd2-4b0d-8a19-3eade1d3e405");//客户端安装号,UUID
        //headers.put("mu", "460002888255671");//访客ID-可选
//        headers.put("phone", SystemUtil.getImei(this));//IMEI
//        headers.put("ua", DefaultHttpHeader.getUA(this));//设备信息,例如：android-19-1080x1920-SM-G9008V
//        headers.put("X-Up-Bearer-Type", NetworkManager.getCurrentNetworkName(this));//网络接入类型,"WLAN"
//        HttpParams params = new HttpParams();
//        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
//        params.put("commonParamsKey2", "这里支持中文参数");
        //-----------------------------------------------------------------------------------//

        //必须调用初始化
        OkGo.init(this);

        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            int defautTimeOut=10000;//10s
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()

                    //打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行
                    //.debug("OkHttpUtils")//// TODO: 16/11/18 发包时关闭debug开关

                    //如果使用默认的60秒,以下三行也不需要传
                    .setConnectTimeout(defautTimeOut)  //全局的连接超时时间
                    .setReadTimeOut(defautTimeOut)     //全局的读取超时时间
                    .setWriteTimeOut(defautTimeOut)    //全局的写入超时时间

                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy0216/
                    .setCacheMode(CacheMode.NO_CACHE)

                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                    //如果不想让框架管理cookie,以下不需要
                    .setCookieStore(new MemoryCookieStore())                //cookie使用内存缓存（app退出后，cookie消失）
//                    .setCookieStore(new PersistentCookieStore())          //cookie持久化存储，如果cookie不过期，则一直有效

                    //可以设置https的证书,以下几种方案根据需要自己设置
//                    .setCertificates()                                  //方法一：信任所有证书（选一种即可）
//                    .setCertificates(getAssets().open("srca.cer"))      //方法二：也可以自己设置https证书（选一种即可）
//                    .setCertificates(getAssets().open("aaaa.bks"), "123456", getAssets().open("srca.cer"))//方法三：传入bks证书,密码,和cer证书,支持双向加密

                    //可以添加全局拦截器,不会用的千万不要传,错误写法直接导致任何回调不执行
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return chain.proceed(chain.request());
//                    }
//                })

                    //这两行同上,不需要就不要传
                    .addCommonHeaders(headers);                                       //设置全局公共头
                    //.addCommonParams(params);                                          //设置全局公共参数
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 设置应用下载目录
    private void setDownLoadPath(){
        String filepath;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){// 判断SD卡是否可用
            // SDCard/MM/Download
            filepath= Environment.getExternalStorageDirectory().getAbsoluteFile()+ File.separator+"hzb"+"Download";
        }else {
            //data/data/应用的包名/files/
            filepath=getApplicationContext().getFilesDir().getAbsolutePath();
        }
        DownloadService.getDownloadManager().setTargetFolder(filepath);
    }

    // 获取MetaValue配置的渠道值
    private String getApplicationMetaValue(String name) {
        String value= "";
        try {
            ApplicationInfo appInfo =getPackageManager()
                    .getApplicationInfo(getPackageName(),
                            PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    // 获取渠道号
    public static String getChannel(){
        return channel;
    }

    /**
     * 上下文
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }


//    @Override
//    public void onTerminate() {
//        // 程序终止的时候进行配置保存 不能确保每次都调用到。。。。
//        saveConfig();
//        super.onTerminate();
//    }
}
