package dev.tberghuis.btmacrokb.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
//
//class PreferencesRepository(private val dataStore: DataStore<Preferences>) {
//  val encryptionPasswordFlow: Flow<String> = dataStore.data.map { preferences ->
//    preferences[KEY_ENCRYPTION_PASSWORD] ?: run {
//      val random = java.util.UUID.randomUUID().toString().split("-")[0]
//      setEncryptionPassword(random)
//      random
//    }
//  }.distinctUntilChanged()
//
//  suspend fun setEncryptionPassword(s: String) {
//    dataStore.edit { settings ->
//      settings[KEY_ENCRYPTION_PASSWORD] = s
//    }
//  }
//
//  companion object {
//    private val KEY_ENCRYPTION_PASSWORD = stringPreferencesKey("encryption_password")
//
//    @Volatile
//    private var instance: PreferencesRepository? = null
//    fun getInstance(context: Context) =
//      instance ?: synchronized(this) {
//        instance ?: PreferencesRepository(context.dataStore).also { instance = it }
//      }
//  }
//}