package com.example.moneytracker.core.common

object Constants {
    const val DATABASE_NAME = "expense_tracker.db"
    const val PREFERENCES_NAME = "expense_tracker_preferences"

    const val DEFAULT_CURRENCY = "UAH"
    const val DEFAULT_CURRENCY_SYMBOL = "₴"

    const val DATE_FORMAT = "yyyy-MM-dd"
    const val DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val DISPLAY_DATE_FORMAT = "dd MMM yyyy"
    const val DISPLAY_DATE_TIME_FORMAT = "dd MMM yyyy, HH:mm"
}

object PreferencesKeys {
    const val USER_ID = "user_id"
    const val THEME_MODE = "theme_mode"
    const val CURRENCY = "currency"
}