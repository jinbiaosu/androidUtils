package com.vein.demo.demo.sp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;

import com.vein.demo.R;
import com.vein.vlibs.db.sp.Preference;
import com.vein.vlibs.db.sp.RxSharedPreferences;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by vein on 17/3/12.
 */


public class SPSampleActivity extends Activity {

//    @BindView(R.id.foo_1) CheckBox foo1Checkbox;
//    @BindView(R.id.foo_2) CheckBox foo2Checkbox;
    Preference<Boolean> fooPreference;
    CompositeDisposable disposables;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Views
        setContentView(R.layout.sample_activity);
        ButterKnife.bind(this);

        // Preferences
        SharedPreferences preferences = getDefaultSharedPreferences(this);
        RxSharedPreferences rxPreferences = RxSharedPreferences.create(preferences);
        // foo
        fooPreference = rxPreferences.getBoolean("foo");
//        fooPreference.asObservable().subscribe(new Action1<boolean>() {
//
//            @Override
//            public void call(Boolean aBoolean) {
//
//            }
//        });

    }

    @Override protected void onResume() {
        super.onResume();

        disposables = new CompositeDisposable();
//        bindPreference(foo1Checkbox, fooPreference);
//        bindPreference(foo2Checkbox, fooPreference);
    }

    @Override protected void onPause() {
        super.onPause();
        disposables.dispose();
    }

    void bindPreference(final CheckBox checkBox, Preference<Boolean> preference) {
        // Bind the preference to the checkbox.
//        disposables.add(preference.asObservable()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(checkBox::setChecked));
        // Bind the checkbox to the preference.
//        disposables.add(RxJavaInterop.toV2Observable(RxCompoundButton.checkedChanges(checkBox))
//                .skip(1) // First emission is the original state.
//                .subscribe(preference.asConsumer()));
    }
}