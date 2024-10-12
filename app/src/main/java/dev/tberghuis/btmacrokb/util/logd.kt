package dev.tberghuis.btmacrokb.util

import android.util.Log
import dev.tberghuis.btmacrokb.BuildConfig

fun logd(s: String) {
  if (BuildConfig.DEBUG) {
    Log.d("xxx", s)
  }
}