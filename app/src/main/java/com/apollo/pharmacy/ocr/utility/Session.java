package com.apollo.pharmacy.ocr.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setfixedtimeperiod(Long fixedtimeperiod) {
        prefs.edit().putLong("FIXEDTIMEPERIOD", fixedtimeperiod).commit();
    }

    public Long getfixedtimeperiod() {
        Long fixedtimeperiod = prefs.getLong("FIXEDTIMEPERIOD", 0);
        return fixedtimeperiod;
    }

    public void settimeperiod1(Long timeperiod1) {
        prefs.edit().putLong("TIMEPERIOD1", timeperiod1).commit();
    }

    public Long getimeperiod1() {
        Long timeperiod1 = prefs.getLong("TIMEPERIOD1", 0);
        return timeperiod1;
    }
}
