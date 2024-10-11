package dev.tberghuis.btmacrokb

import android.util.Log

fun logd(s: String) {
  if (BuildConfig.DEBUG) {
    Log.d("xxx", s)
  }
}