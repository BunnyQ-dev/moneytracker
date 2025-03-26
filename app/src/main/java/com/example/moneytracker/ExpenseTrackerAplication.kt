package com.example.moneytracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import java.util.TimeZone

@HiltAndroidApp
class ExpenseTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Set default timezone to UTC
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }
}