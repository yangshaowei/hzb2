package com.cbs.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CommonUtils {

	public static String getAppVersion(Context c) {
		PackageManager pm = c.getPackageManager();
		String version = null;
		try {
			PackageInfo pi = pm.getPackageInfo(c.getPackageName(), 0);
			version = Integer.toString(pi.versionCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (version == null) {
			version = "";
		}
		return version;
	}

	public static String collectExtraCrashInfo(Context context) {
		if (context == null) {
			return "";
		}

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("OS Version:")
				.append(Build.VERSION.RELEASE).append('_')
				.append(Build.VERSION.SDK_INT).append('\n');
		stringBuilder.append("Model:").append(Build.MODEL)
				.append('\n');
		stringBuilder.append("Product:").append(Build.PRODUCT)
				.append('\n');
		stringBuilder.append("Manufacturer:")
				.append(Build.MANUFACTURER).append('\n');
		stringBuilder.append("CPU:").append(Build.CPU_ABI)
				.append('\n');

		try {
			final ActivityManager activityManager = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
			List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
			if (runningTaskInfos != null && runningTaskInfos.size() > 0) {
				ActivityManager.RunningTaskInfo runningTaskInfo = runningTaskInfos.get(0);
				stringBuilder.append("activity top task info:\n");
				stringBuilder.append("task id " + runningTaskInfo.id + "\n");
				stringBuilder.append("task description " + runningTaskInfo.description + "\n");
				stringBuilder.append("task numActivities " + runningTaskInfo.numActivities + "\n");
				stringBuilder.append("task baseActivity " + runningTaskInfo.baseActivity.getClassName() + "\n");
				stringBuilder.append("task topActivity " + runningTaskInfo.topActivity.getClassName() + "\n");
			}
		} catch (Exception e) {
			Log.e("AndroidRuntime", "error occured when collect info", e);
		}
		return stringBuilder.toString();
	}

	public static String getVersionName(Context c) {
		PackageManager pm = c.getPackageManager();
		String versionName = null;
		try {
			PackageInfo pi = pm.getPackageInfo(c.getPackageName(), 0);
			versionName = pi.versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (versionName == null) {
			versionName = "";
		}
		return versionName;
	}

	public static String getVersionCode(Context c) {
		PackageManager pm = c.getPackageManager();
		String version = null;
		try {
			PackageInfo pi = pm.getPackageInfo(c.getPackageName(), 0);
			version = Integer.toString(pi.versionCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (version == null) {
			version = "";
		}
		return version;
	}

	public static void saveAssetFile(Context context, String sourceName,
									 String desFilePath) {
		AssetManager assetManager = context.getAssets();
		InputStream in = null;
		OutputStream out = null;
		try {
			in = assetManager.open(sourceName);
			out = new FileOutputStream(desFilePath);

			byte[] buffer = new byte[1024];
			int read;
			while ((read = in.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setActivityFullScreen(Activity activity,
			boolean fullScreen) {
		Window window = activity.getWindow();
		WindowManager.LayoutParams attrs = window.getAttributes();
		if (fullScreen) {
			attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
			window.setAttributes(attrs);
		} else {
			attrs.flags &= ~(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			window.setAttributes(attrs);
		}
	}

	public static boolean isAppOnForeground(Context context) {
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		if (appProcesses == null)
			return false;
		String packageName = context.getPackageName();
		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}
		return false;
	}

	public static String getIPAddress(boolean useIPv4) {
		try {
			List<NetworkInterface> interfaces = Collections
					.list(NetworkInterface.getNetworkInterfaces());
			for (NetworkInterface intf : interfaces) {
				List<InetAddress> addrs = Collections.list(intf
						.getInetAddresses());
				for (InetAddress addr : addrs) {
					if (!addr.isLoopbackAddress()) {
						String sAddr = addr.getHostAddress().toUpperCase(
								Locale.getDefault());
						//boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
						boolean isIPv4 = addr instanceof Inet4Address;
						if (useIPv4) {
							if (isIPv4)
								return sAddr;
						} else {
							if (!isIPv4) {
								int delim = sAddr.indexOf('%'); // drop ip6 port
																// suffix
								return delim < 0 ? sAddr : sAddr.substring(0,
										delim);
							}
						}
					}
				}
			}
		} catch (Exception ex) {
		} // for now eat exceptions
		return "";
	}

	public static String getProcessName(Context context) {
		int pid = android.os.Process.myPid();
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		if(appProcesses != null){
			for (RunningAppProcessInfo appProcess : appProcesses) {
				if (appProcess.pid == pid) {
					return appProcess.processName;
				}
			}
		}
		return "";
	}

	public static boolean isRcsServiceRuning(Context context) {
		
		ActivityManager manager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> list = manager.getRunningServices(40);
		for(int i=0; i< list.size(); i++) {
			RunningServiceInfo info = list.get(i);
			String serviceName = info.service.getClassName();
			if(serviceName.equals("com.chinasofti.rcs.business.service.RcsService")) {
				Log.e("LHR", "     getServiceName..name:"+info.service.getClassName());
				return true;
			}
		}
		return false;
	}

	/*
	 * 方法名：isPhoneNumber(String num)
	 * 功   能：判断是否是手机号码
	 * 参   数：String num 手机号
	 * 返回值：是或否
	 */
	public static boolean isPhoneNumber(String num) {
//		String reg = "^((86)|(086)|(0086)|(\\+86)){0,1}((13[0-9])|(15[^4,\\D])|(177)|(173)|(18[0-9]))\\d{8}$";
		String reg = "^[\\d]{11}";
		return !TextUtils.isEmpty(num) ? num.matches(reg) : false;
	}


	/**
	 * 判断一个字符串是否为纯数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isStringAreNumber(String str) {
		if (TextUtils.isEmpty(str)) {
			return false;
		}
		return str.matches("[0-9]+");

	}

	private static long lastClickTime;
	// 防止控件被快速点击多次
	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if ( 0 < timeD && timeD < 800) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	/**
	 * 根据包名判断应用是否安装
	 */
	public static boolean checkApkExist(Context context, String packageName) {
		if (TextUtils.isEmpty(packageName))
			return false;
		try {
			ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
			return true;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}

	/**
	 * 网络是否已连接
	 *
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		NetworkInfo networkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE))
				.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnected();
	}

}
