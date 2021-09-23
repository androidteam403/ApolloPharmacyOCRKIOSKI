package com.apollo.pharmacy.ocr.activities

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apollo.pharmacy.ocr.R
import com.apollo.pharmacy.ocr.utility.LanguageManager.Companion.setLocale

open class BaseActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(setLocale(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setLocale(this)
    }
}
