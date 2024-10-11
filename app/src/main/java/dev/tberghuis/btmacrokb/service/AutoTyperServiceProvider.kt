package dev.tberghuis.btmacrokb.service

import android.app.Application
import android.content.Context

@Volatile
private var INSTANCE: BoundService<MyBtService>? = null
private val lock = Any()
private fun getInstance(application: Application): BoundService<MyBtService> {
  synchronized(lock) {
    return INSTANCE ?: BoundService<MyBtService>(application, MyBtService::class.java).also {
      INSTANCE = it
    }
  }
}

suspend fun Context.provideMyBtService(): MyBtService {
  return getInstance(this.applicationContext as Application).provideService()
}