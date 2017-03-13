package com.vein.vlibs.test;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;

import com.vein.vlibs.view.pickerview.TimePickerView;
import com.vein.vlibs.view.pickerview.TimePickerView.OnTimeSelectListener;

import java.util.Date;


/**
 * Created by htjc on 17/1/19.
 */

public class PickerTest extends Activity {
    private TimePickerView mTimePickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createTimerPicker();
        mTimePickerView.show();
    }


    private void createTimerPicker() {
        mTimePickerView = new TimePickerView(this, TimePickerView.Type.MONTH_DAY_HOUR_MIN);

        Time time = new Time();
        time.setToNow();
        mTimePickerView.setRange(time.year, time.year + 30);

        mTimePickerView.setCyclic(false);
        mTimePickerView.setCancelable(true);

        mTimePickerView.setTime(new Date());
        mTimePickerView.setOnTimeSelectListener(new OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
//				mDate = date;
//				DZLogUtil.e("", "======notice==date==" + DateFormat.format("yyyy-MM-dd-HH-mm", date));
//				String format = "MM " + resource.getString("pickerview_month") + " dd " + resource.getString("pickerview_day") + " HH "
//						+ resource.getString("pickerview_hours") + " mm " + resource.getString("pickerview_minutes");
//				mTimeTv.setText(DateFormat.format(format, date).toString());
            }
        });
    }

}
