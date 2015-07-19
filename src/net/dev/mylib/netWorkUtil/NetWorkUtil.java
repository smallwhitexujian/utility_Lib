package net.dev.mylib.netWorkUtil;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;  
import android.content.Intent;
import android.net.ConnectivityManager;  
import android.net.NetworkInfo;  
import android.net.NetworkInfo.State;  
import android.net.wifi.WifiManager;  

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;

import net.dev.mylib.DebugLogs;

/**
 * @author xujian
 * 网络检测工具类
 */
public class NetWorkUtil {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	public static final int STATE_DISCONNECT = 0;
	public static final int STATE_WIFI = 1;
	public static final int STATE_MOBILE = 2;

	public static String concatUrlParams() {
		return null;
	}

	public static String encodeUrl() {
		return null;
	}
	
	/**
	 * 打开网络设置界面
	 */
	public static void openSetting(Activity activity) {
		Intent intent = new Intent("/");
		ComponentName cm = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
		intent.setComponent(cm);
		intent.setAction("android.intent.action.VIEW");
		activity.startActivityForResult(intent, 0);
	}

	/**
	 * 是否有网络连接
	 * @param context
	 * @return
	 */
	public static boolean isNetWorkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] nis = cm.getAllNetworkInfo();
		if (nis != null) {
			for (NetworkInfo ni : nis) {
				if (ni != null) {
					if (ni.isConnected()) {
						return true;
					}
				}
			}
		}

		return false;
	}
	
	/**
	 * 判断网络是否连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnected(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (null != connectivity) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (null != info && info.isConnected()) {
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * 是否是WiFi连接
	 * @param context
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		boolean isWifiEnable = wifiMgr.isWifiEnabled();
		return isWifiEnable;
	}
	
	/**
	 * btyeArray转化成String
	 * @param b
	 * @return
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * byte转化为String 
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * MD5加密
	 * @param origin
	 * @return
	 */
	public static String md5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = new String(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	public static String md5EncodeToHexString(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	public static int getNetworkState(Context context) {
		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		// Wifi
		State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
		if (state == State.CONNECTED || state == State.CONNECTING) {
			return STATE_WIFI;
		}
		// 3G
		state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
		if (state == State.CONNECTED || state == State.CONNECTING) {
			return STATE_MOBILE;
		}
		return STATE_DISCONNECT;
	}
	
	
	/**
	 * @category 判断是否有外网连接（普通方法不能判断外网的网络是否连接，比如连接上局域网）
	 * @return
	 */
	public static final boolean ping() {
		String result = null;
		try {
			String ip = "www.baidu.com";// ping 的地址，可以换成任何一种可靠的外网
			Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);// ping网址3次
			// 读取ping的内容，可以不加
			InputStream input = p.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			StringBuffer stringBuffer = new StringBuffer();
			String content = "";
			while ((content = in.readLine()) != null) {
				stringBuffer.append(content);
			}
			DebugLogs.d(content, new Throwable("test"));
			DebugLogs.d("------ping-----result content : "+stringBuffer.toString());
			// ping的状态
			int status = p.waitFor();
			if (status == 0) {
				result = "success";
				return true;
			} else {
				result = "failed";
			}
		} catch (IOException e) {
			result = "IOException";
		} catch (InterruptedException e) {
			result = "InterruptedException";
		} finally {
			DebugLogs.d("result = " + result);
		}
		return false;
	}
}
