package net.dev.mylib.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.dev.mylib.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

public class DateTimePicker extends FrameLayout {
	private NumberPicker mDateSpinner;
	private NumberPicker mHourSpinner;
	private NumberPicker mMinuteSpinner;
	private NumberPicker mDateYear;
	private Calendar mDate;
	private int mHour, mMinute,mYear;
	private String[] mDateDisplayValues = new String[7];
	private OnDateTimeChangedListener mOnDateTimeChangedListener;

	/**
	 * 只显示年月和星期
	 * @param context
	 * @param isDate
	 */
	public DateTimePicker(Context context ,boolean isDate){
		super(context);
		if (isDate) {
			mDate = Calendar.getInstance();

			mHour = mDate.get(Calendar.HOUR_OF_DAY);
			mMinute = mDate.get(Calendar.MINUTE);

			inflate(context, R.layout.datedialog, this);
			mDateYear = (NumberPicker)this.findViewById(R.id.np_year);
			mDateYear.setMinValue(1950);
			mDateYear.setMaxValue(2037);
			updateYearControl();
			mDateYear.setOnValueChangedListener(mOnYearChangedListener);

			mDateSpinner = (NumberPicker) this.findViewById(R.id.np_date);
			mDateSpinner.setMinValue(0);
			mDateSpinner.setMaxValue(6);
			updateDateControl();
			mDateSpinner.setOnValueChangedListener(mOnDateChangedListener);
			TextView textView = (TextView) this.findViewById(R.id.semicolon);
			textView.setVisibility(View.GONE);
			mHourSpinner = (NumberPicker) this.findViewById(R.id.np_hour);
			mHourSpinner.setVisibility(View.GONE);
			mMinuteSpinner = (NumberPicker) this.findViewById(R.id.np_minute);
			mMinuteSpinner.setVisibility(View.GONE);
		}
	}
	
	/**
	 * 显示年月星期和小时
	 * @param context
	 */
	public DateTimePicker(Context context) {
		super(context);
		mDate = Calendar.getInstance();

		mHour = mDate.get(Calendar.HOUR_OF_DAY);
		mMinute = mDate.get(Calendar.MINUTE);

		inflate(context, R.layout.datedialog, this);
		mDateYear = (NumberPicker)this.findViewById(R.id.np_year);
		mDateYear.setMinValue(2000);
		mDateYear.setMaxValue(2037);
		updateYearControl();
		mDateYear.setOnValueChangedListener(mOnYearChangedListener);

		mDateSpinner = (NumberPicker) this.findViewById(R.id.np_date);
		mDateSpinner.setMinValue(0);
		mDateSpinner.setMaxValue(6);
		updateDateControl();
		mDateSpinner.setOnValueChangedListener(mOnDateChangedListener);

		mHourSpinner = (NumberPicker) this.findViewById(R.id.np_hour);
		mHourSpinner.setMaxValue(23);
		mHourSpinner.setMinValue(0);
		mHourSpinner.setValue(mHour);
		mHourSpinner.setOnValueChangedListener(mOnHourChangedListener);

		mMinuteSpinner = (NumberPicker) this.findViewById(R.id.np_minute);
		mMinuteSpinner.setMaxValue(59);
		mMinuteSpinner.setMinValue(0);
		mMinuteSpinner.setValue(mMinute);
		mMinuteSpinner.setOnValueChangedListener(mOnMinuteChangedListener);
	}
	
	/**
	 * 显示显示小时
	 * @param context
	 */
	public DateTimePicker(Context context,String hours) {
		super(context);
		mDate = Calendar.getInstance();

		mHour = mDate.get(Calendar.HOUR_OF_DAY);
		mMinute = mDate.get(Calendar.MINUTE);

		inflate(context, R.layout.datedialog, this);
		mDateYear = (NumberPicker)this.findViewById(R.id.np_year);
		mDateYear.setVisibility(View.GONE);

		mDateSpinner = (NumberPicker) this.findViewById(R.id.np_date);
		mDateSpinner.setVisibility(View.GONE);

		mHourSpinner = (NumberPicker) this.findViewById(R.id.np_hour);
		mHourSpinner.setMaxValue(23);
		mHourSpinner.setMinValue(0);
		mHourSpinner.setValue(mHour);
		mHourSpinner.setOnValueChangedListener(mOnHourChangedListener);

		mMinuteSpinner = (NumberPicker) this.findViewById(R.id.np_minute);
		mMinuteSpinner.setMaxValue(59);
		mMinuteSpinner.setMinValue(0);
		mMinuteSpinner.setValue(mMinute);
		mMinuteSpinner.setOnValueChangedListener(mOnMinuteChangedListener);
	}
	private NumberPicker.OnValueChangeListener mOnYearChangedListener = new OnValueChangeListener() {
		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			mYear = mDateYear.getValue();
			onDateTimeChanged();
		}
	};

	private NumberPicker.OnValueChangeListener mOnDateChangedListener = new OnValueChangeListener() {
		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			mDate.add(Calendar.DAY_OF_MONTH, newVal - oldVal);
			updateDateControl();
			onDateTimeChanged();
		}
	};

	private NumberPicker.OnValueChangeListener mOnHourChangedListener = new OnValueChangeListener() {
		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			mHour = mHourSpinner.getValue();
			onDateTimeChanged();
		}
	};

	private NumberPicker.OnValueChangeListener mOnMinuteChangedListener = new OnValueChangeListener() {
		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			mMinute = mMinuteSpinner.getValue();
			onDateTimeChanged();
		}
	};

	@SuppressLint("SimpleDateFormat")
	private void updateYearControl() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		mDateYear.setValue(Integer.valueOf(str));
	}

	private void updateDateControl() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(mDate.getTimeInMillis());
		cal.add(Calendar.DAY_OF_YEAR, -7 / 2 - 1);
		mDateSpinner.setDisplayedValues(null);
		for (int i = 0; i < 7; ++i) {
			cal.add(Calendar.DAY_OF_YEAR, 1);
			mDateDisplayValues[i] = (String) DateFormat.format("MM-dd",cal);
		}
		mDateSpinner.setDisplayedValues(mDateDisplayValues);
		mDateSpinner.setValue(7 / 2);
		mDateSpinner.invalidate();
	}

	public interface OnDateTimeChangedListener {
		void onDateTimeChanged(DateTimePicker view, int year, int month,
				int day, int hour, int minute);
	}

	public void setOnDateTimeChangedListener(OnDateTimeChangedListener callback) {
		mOnDateTimeChangedListener = callback;
	}

	private void onDateTimeChanged() {
		if (mOnDateTimeChangedListener != null) {
			mOnDateTimeChangedListener.onDateTimeChanged(this,
					mYear, mDate.get(Calendar.MONTH),
					mDate.get(Calendar.DAY_OF_MONTH), mHour, mMinute);
		}
	}
}
