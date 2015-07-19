package net.dev.mylib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @author xujian
 * 常用工具
 */
public class Utility {
	
	/**
	 * 获取应用程序名称
	 */
	public static String getAppName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [获取应用程序版本名称信息]
	 * 
	 * @param context
	 * @return 当前应用的版本名称
	 */
	public static String getVersionName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 打卡软键盘
	 * 
	 * @param mEditText
	 *            输入框
	 * @param mContext
	 *            上下文
	 */
	public static void openKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * 关闭软键盘
	 * 
	 * @param mEditText
	 *            输入框
	 * @param mContext
	 *            上下文
	 */
	public static void closeKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
	}

	/**
	 * 手机号码格式匹配
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((17[0-9])|((13[0-9])|(15[^4,\\D])|(18[0,1,3,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		System.out.println(m.matches() + "-telnum-");
		return m.matches();
	}

	/**
	 * 是否含有指定字符
	 * 
	 * @param expression
	 * @param text
	 * @return
	 */
	@SuppressWarnings("unused")
	private static boolean matchingText(String expression, String text) {
		Pattern p = Pattern.compile(expression);
		Matcher m = p.matcher(text);
		boolean b = m.matches();
		return b;
	}

	/**
	 * 邮政编码
	 * 
	 * @param zipcode
	 * @return
	 */
	public static boolean isZipcode(String zipcode) {
		Pattern p = Pattern.compile("[0-9]\\d{5}");
		Matcher m = p.matcher(zipcode);
		System.out.println(m.matches() + "-zipcode-");
		return m.matches();
	}

	/**
	 * 邮件格式
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		Pattern p = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		Matcher m = p.matcher(email);
		System.out.println(m.matches() + "-email-");
		return m.matches();
	}

	/**
	 * 固话号码格式
	 * 
	 * @param telfix
	 * @return
	 */
	public static boolean isTelfix(String telfix) {
		Pattern p = Pattern.compile("d{3}-d{8}|d{4}-d{7}");
		Matcher m = p.matcher(telfix);
		System.out.println(m.matches() + "-telfix-");
		return m.matches();
	}

	/**
	 * 用户名匹配
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isCorrectUserName(String name) {
		Pattern p = Pattern.compile("([A-Za-z0-9]){2,10}");
		Matcher m = p.matcher(name);
		System.out.println(m.matches() + "-name-");
		return m.matches();
	}

	/**
	 * 密码匹配，以字母开头，长度 在6-18之间，只能包含字符、数字和下划线。
	 * 
	 * @param pwd
	 * @return
	 * 
	 */
	public static boolean isCorrectUserPwd(String pwd) {
		Pattern p = Pattern.compile("\\w{6,18}");
		Matcher m = p.matcher(pwd);
		System.out.println(m.matches() + "-pwd-");
		return m.matches();
	}

	/**
	 * 检查是否存在SDCard
	 * 
	 * @return
	 */
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
	
	/** 
     * 获取手机Imei号 
     *  
     * @param context 
     * @return 
     */  
    public static String getImeiCode(Context context) {  
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);  
        return tm.getDeviceId();  
    }  
  
    /** 
     * @author sunglasses 
     * @param listView 
     * @category 计算mListview的高度 
     */  
    public static void setListViewHeightBasedOnChildren(ListView listView) {  
        ListAdapter listAdapter = listView.getAdapter();  
        if (listAdapter == null) {  
            // pre-condition  
            return;  
        }  
        int totalHeight = 0;  
        for (int i = 0; i < listAdapter.getCount(); i++) {  
            View listItem = listAdapter.getView(i, null, listView);  
            listItem.measure(0, 0);  
            totalHeight += listItem.getMeasuredHeight();  
        }  
        ViewGroup.LayoutParams params = listView.getLayoutParams();  
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));  
        listView.setLayoutParams(params);  
    }  
}
