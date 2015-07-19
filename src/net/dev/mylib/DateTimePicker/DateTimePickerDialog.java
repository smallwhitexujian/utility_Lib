package net.dev.mylib.DateTimePicker;

import java.util.Calendar;
import net.dev.mylib.DateTimePicker.DateTimePicker.OnDateTimeChangedListener;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.format.DateUtils;

/**
 * public void showDialog() {
 *		DateTimePickerDialog dialog = new DateTimePickerDialog(this,System.currentTimeMillis(),true);
 *		dialog.setOnDateTimeSetListener(new OnDateTimeSetListener() {
 *			public void OnDateTimeSet(AlertDialog dialog, long date) {
 *				Toast.makeText(MainActivity.this,"当前选择的时间" + getStringDate(date), Toast.LENGTH_LONG).show();
 *			}
 *		});
 *		dialog.show();
 *	}
 *
 */
public class DateTimePickerDialog extends AlertDialog implements OnClickListener {
	private DateTimePicker mDateTimePicker;
	private Calendar mDate = Calendar.getInstance();
	private OnDateTimeSetListener mOnDateTimeSetListener;

	@SuppressWarnings("deprecation")
	public DateTimePickerDialog(Context context, long date) {
		super(context);
		mDateTimePicker = new DateTimePicker(context);
		setView(mDateTimePicker);
		mDateTimePicker.setOnDateTimeChangedListener(new OnDateTimeChangedListener() {
					@Override
					public void onDateTimeChanged(DateTimePicker view,
							int year, int month, int day, int hour, int minute) {
						mDate.set(Calendar.YEAR, year);
						mDate.set(Calendar.MONTH, month);
						mDate.set(Calendar.DAY_OF_MONTH, day);
						mDate.set(Calendar.HOUR_OF_DAY, hour);
						mDate.set(Calendar.MINUTE, minute);
						mDate.set(Calendar.SECOND, 0);
						updateTitle(mDate.getTimeInMillis());
					}
				});

		setButton("确认", this);
//		setButton2("取消", (OnClickListener) null);
		mDate.setTimeInMillis(date);
		updateTitle(mDate.getTimeInMillis());
	}
	
	public DateTimePickerDialog(Context context, long date,boolean isDate) {
		super(context);
		mDateTimePicker = new DateTimePicker(context,isDate);
		setView(mDateTimePicker);
		mDateTimePicker.setOnDateTimeChangedListener(new OnDateTimeChangedListener() {
					@Override
					public void onDateTimeChanged(DateTimePicker view,
							int year, int month, int day, int hour, int minute) {
						mDate.set(Calendar.YEAR, year);
						mDate.set(Calendar.MONTH, month);
						mDate.set(Calendar.DAY_OF_MONTH, day);
						mDate.set(Calendar.HOUR_OF_DAY, hour);
						mDate.set(Calendar.MINUTE, minute);
						mDate.set(Calendar.SECOND, 0);
						updateTitle(mDate.getTimeInMillis());
					}
				});

		setButton("确认", this);
//		setButton2("取消", (OnClickListener) null);
		mDate.setTimeInMillis(date);
		updateTitle(mDate.getTimeInMillis());
	}
	
	public DateTimePickerDialog(Context context, long date,String house) {
		super(context);
		mDateTimePicker = new DateTimePicker(context,house);
		setView(mDateTimePicker);
		mDateTimePicker.setOnDateTimeChangedListener(new OnDateTimeChangedListener() {
					@Override
					public void onDateTimeChanged(DateTimePicker view,
							int year, int month, int day, int hour, int minute) {
						mDate.set(Calendar.YEAR, year);
						mDate.set(Calendar.MONTH, month);
						mDate.set(Calendar.DAY_OF_MONTH, day);
						mDate.set(Calendar.HOUR_OF_DAY, hour);
						mDate.set(Calendar.MINUTE, minute);
						mDate.set(Calendar.SECOND, 0);
						updateTitle(mDate.getTimeInMillis());
					}
				});

		setButton("确认", this);
//		setButton2("取消", (OnClickListener) null);
		mDate.setTimeInMillis(date);
		updateTitle(mDate.getTimeInMillis());
	}

	public interface OnDateTimeSetListener {
		void OnDateTimeSet(AlertDialog dialog, long date);
	}

	private void updateTitle(long date) {
		int flag = DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE
				| DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_SHOW_TIME;
		setTitle(DateUtils.formatDateTime(this.getContext(), date, flag));
	}

	public void setOnDateTimeSetListener(OnDateTimeSetListener callBack) {
		mOnDateTimeSetListener = callBack;
	}

	public void onClick(DialogInterface arg0, int arg1) {
		if (mOnDateTimeSetListener != null) {
			mOnDateTimeSetListener.OnDateTimeSet(this, mDate.getTimeInMillis());
		}
	}
}
