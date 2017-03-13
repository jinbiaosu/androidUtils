package com.vein.vlibs.db.sp;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

final class StringAdapter implements Preference.Adapter<String> {
  static final StringAdapter INSTANCE = new StringAdapter();

  @Override public String get(@NonNull String key, @NonNull SharedPreferences preferences) {
    String value = preferences.getString(key, null);
    assert value != null; // Not called unless key is present.
    return value;
  }

  @Override public void set(@NonNull String key, @NonNull String value,
      @NonNull SharedPreferences.Editor editor) {
    editor.putString(key, value);
  }
}
