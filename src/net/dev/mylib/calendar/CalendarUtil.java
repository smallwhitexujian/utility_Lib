package net.dev.mylib.calendar;

import java.math.BigDecimal;
import java.util.Calendar;

import android.text.TextUtils;

/**
 * @author xujian
 * 
 */
public class CalendarUtil {
	private static CalendarUtil calendar;

	private CalendarUtil() {
	}

	public static CalendarUtil getInstance() {
		if (calendar == null) {
			calendar = new CalendarUtil();
		}
		return calendar;
	}
	/**
	 * 计算运动消耗卡路里
	 * @param totalCount
	 * @param calury
	 * @param unit_time
	 * @return
	 */
	public int calCalOrie(int totalCount, String calury, String unit_time) {
		if (TextUtils.isEmpty(calury) || TextUtils.isEmpty(unit_time)) {
			return 0;
		}
		Integer caluryDouble = Integer.parseInt(calury);
		Integer unitTimeDouble = Integer.parseInt(unit_time);
		return (int) caluryDouble * totalCount / unitTimeDouble;
	}

	/**
	 * 
	 * 计算保留多位小数
	 * 
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param scale
	 *            设置保留的小数位数
	 * @param divider
	 *            除数
	 * @param dividedScale
	 *            被除数
	 * @return 返回 float
	 */
	public static float setScaleValue(int scale, int divider) {
		float ft;
		int roundingMode = 4;// 表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
		BigDecimal bd = new BigDecimal((double) divider);
		bd = bd.setScale(3, roundingMode);
		ft = bd.floatValue();
		return ft;
	}
	/**
	 * 获取当前时间
	 * @return long
	 */
	public static final long getToDay(){
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		long Calendartime = cal.getTimeInMillis();
		return Calendartime;
	}
	
}
