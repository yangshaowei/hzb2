package com.cbs.common.utils;

import android.content.Context;
import android.location.Location;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author mayuechen
 *
 */
public class StringUtil {
	/**
	 * Native to ascii string. It's same as execut native2ascii.exe.
	 * 
	 * @param str
	 *            native string
	 * @return ascii string
	 */
	public static String native2Ascii(String str) {
		char[] chars = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			sb.append(char2Ascii(chars[i]));
		}
		return sb.toString();
	}

	/**
	 * Native character to ascii string.
	 * 
	 * @param c
	 *            native character
	 * @return ascii string
	 */
	private static String char2Ascii(char c) {
		if (c > 255) {
			StringBuilder sb = new StringBuilder();
			sb.append("\\u");
			int code = (c >> 8);
			String tmp = Integer.toHexString(code);
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
			code = (c & 0xFF);
			tmp = Integer.toHexString(code);
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
			return sb.toString();
		} else {
			return Character.toString(c);
		}
	}

	/**
	 * Ascii to native string. It's same as execut native2ascii.exe -reverse.
	 * 
	 * @param str
	 *            ascii string
	 * @return native string
	 */
	public static String ascii2Native(String str) {
		StringBuilder sb = new StringBuilder();
		int begin = 0;
		int index = str.indexOf("\\u");
		while (index != -1) {
			sb.append(str.substring(begin, index));
			sb.append(ascii2Char(str.substring(index, index + 6)));
			begin = index + 6;
			index = str.indexOf("\\u", begin);
		}
		sb.append(str.substring(begin));
		return sb.toString();
	}

	/**
	 * Ascii to native character.
	 * 
	 * @param str
	 *            ascii string
	 * @return native character
	 */
	private static char ascii2Char(String str) {
		if (str.length() != 6) {
			throw new IllegalArgumentException(
					"Ascii string of a native character must be 6 character.");
		}
		if (!"\\u".equals(str.substring(0, 2))) {
			throw new IllegalArgumentException(
					"Ascii string of a native character must start with \"\\u\".");
		}
		String tmp = str.substring(2, 4);
		int code = Integer.parseInt(tmp, 16) << 8;
		tmp = str.substring(4, 6);
		code += Integer.parseInt(tmp, 16);
		return (char) code;
	}

	/** 转换 url, 返回宽为 120 像素的缩略图的 url */
	public static String toThumbUrl(String imageUrl) {
		final String url = imageUrl;
		try {
			if (!TextUtils.isEmpty(imageUrl)) {
				String[] datas = url.split("_");
				if (datas != null && datas.length == 3) {
					String numberFormat = datas[1];
					if (!TextUtils.isEmpty(numberFormat)) {
						String[] numbers = numberFormat.split("-");
						if (numbers != null && numbers.length == 2) {
							StringBuilder sb = new StringBuilder();
							sb.append(datas[0]);
							sb.append("_");
							sb.append("120" + "-" + numbers[1]);
							sb.append("_");
							sb.append(datas[2]);
							return sb.toString();
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return url;
	}

	/**
	 * 根据两个地方的经度和纬度算出之间的距离
	 */
	public static String getDistanceLatlng(double myLatitude,
										   double myLongitude, double latitude, double longitude) {
		String result = null;
		float[] results = new float[1];
		Location.distanceBetween(myLatitude, myLongitude, latitude, longitude,
				results);
		int distance = (int) results[0];
		if (distance > 100) {
			double d = ((double) distance) / 1000;
			String pre = new DecimalFormat("#.#").format(d);
			result = "距离" + pre + "公里"; // km
		} else {
			result = "本地居民";
		}
		return result;
	}

	/**
	 * String转Int
	 */
	public static int parseInt(String str, int defaultValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * String转long
	 */
	public static long parseLong(String str, long defaultValue) {
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 将小数点转化为百分比
	 */
	public static String dotToPercent(String value) {
		String percent = "";
		float fValue;
		try {
			fValue = Float.parseFloat(value);
			percent = fValue * 100 + "%";
		} catch (Exception e) {
			percent = "0%";
		}
		return percent;
	}

	/**
	 * 判断是否是手机号码
	 */
	public static boolean isPhoneNumber(String num) {
		// String reg2 =
		// "^((861)|(00861)|(\\+861)|1)((3[0-9])|(5[0-3|5-9])|(8[5-9])|82|47|80)[0-9]{8}$";
		String reg = "^((86)|(086)|(0086)|(\\+86)){0,1}((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$";
		return !TextUtils.isEmpty(num) ? num.matches(reg) : false;
	}

	/**
	 * 判断字符串 去掉空格符后 是否是空值，
	 * 
	 * @param value
	 * @return
	 */
	private static final String NULL = "null";
	public static final boolean isEmpty(String value) {
		if (value == null || value.isEmpty()
				|| NULL.equalsIgnoreCase(value)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 去掉最后的空格
	 * @param src
	 * @return
	 */
	public static final String trim(String src){
		if(null == src) return "";
		return src.replaceAll("\\s+$", "");
	}

	/**
	 * 去除字符串中的空格、回车、换行符、制表符
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 号码格式标准化<br>
	 * "00"+国家码+"裸号"
	 * 
	 * @param number
	 * @return
	 */
	public static String formatNumber(String number, Context context) {
		if (!TextUtils.isEmpty(number)) {
			number = number.replace("-", "");
			if (number.startsWith("00")) {
				return "+" + number.substring(2);
			} else if (number.startsWith("0")) {
				return "+" + number.substring(1);
			} else if (number.startsWith("+")) {
				return number;
			} else{
				String ccode = getCountryCode(context);
				if (number.startsWith(ccode)) {
					return "+" + number;
				} else {
					return "+" + ccode + number;
				}
			}
		}
		return "";
	}

	/**
	 * 获取国家码
	 * 
	 * @return
	 */
	private static String COUNTRY_CODE;
	public static String getCountryCode(Context context) {
		if (TextUtils.isEmpty(COUNTRY_CODE)) {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			String cc = tm.getSimCountryIso();
			// 这里只是强制换位中国的，模拟器是美国的。
			cc = "cn";
			if (cc.startsWith("+")) {
				COUNTRY_CODE = cc.substring(1);
			} else {
				if (cc.equalsIgnoreCase("fr")) {
					COUNTRY_CODE = "33";
				} else if (cc.equalsIgnoreCase("cn")) {
					COUNTRY_CODE = "86";
				} else if (cc.equalsIgnoreCase("es")) {
					COUNTRY_CODE = "34";
				} else if (cc.equalsIgnoreCase("fi")) {
					COUNTRY_CODE = "358";
				} else if (cc.equalsIgnoreCase("us")) {
					COUNTRY_CODE = "1";
				} else {
					COUNTRY_CODE = "86";
				}
			}
		}
		return COUNTRY_CODE;
	}

	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static byte[] InputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte imgdata[] = bytestream.toByteArray();
		bytestream.close();
		return imgdata;
	}

	public static String getAssetspath(InputStream is) throws IOException {
		String path = new String(InputStreamToByte(is));
		return path;
	}

	/**
	 * 转换文件大小
	 * 
	 * @param fileS
	 * @return
	 */
	public static String formetFileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#.0");
		String fileSizeString = "";
		if(fileS<0) fileS=0;
		if (fileS <= 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS <= 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS <= 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}
	
	/**
	 * 格式化文件时间
	 * @param time 以毫秒显示的时间值
	 * **/
	public static String formatFileTime(long time) {
		return formatFileTime(new Date(time));
	}
	
	public static String formatFileTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat();

		Calendar nowCalendar = Calendar.getInstance();
		Calendar otherCalendar = Calendar.getInstance();
		otherCalendar.setTime(date);

		// 日期是同一年
		if (nowCalendar.get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR)) {

			// 日期是同一天
			if (nowCalendar.get(Calendar.MONTH) == otherCalendar
					.get(Calendar.MONTH)
					&& nowCalendar.get(Calendar.DATE) == otherCalendar
							.get(Calendar.DATE)) {
				format.applyPattern("HH:mm");
			} else {
				format.applyPattern("MM-dd HH:mm");
			}
		} else {
			format.applyPattern("yyyy-MM-dd");
		}
		
		return format.format(date);
	}

	/**
	 *
	 * @作用:是否包含中文字符
	 * @author: zcc
	 *
	 * @param paramString
	 * @return
	 */
	public static boolean isContainsChinese(String paramString) {
		if (TextUtils.isEmpty(paramString)) {
			return false;
		}
		if (Pattern.compile("[一-龥]").matcher(paramString).find())
			return true;
		if (paramString.contains("【") || paramString.contains("】") || paramString.contains("。"))
			return true;
		return false;
	}
}
