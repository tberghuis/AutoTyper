package dev.tberghuis.btmacrokb.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class PreferencesRepository(private val dataStore: DataStore<Preferences>) {

  val encryptionPasswordFlow: Flow<String> = dataStore.data.map { preferences ->
    preferences[stringPreferencesKey("encryption_password")] ?: run {
      // doitwrong
      val random = java.util.UUID.randomUUID().toString().split("-")[0]
      setEncryptionPassword(random)
      random
    }
  }
  suspend fun setEncryptionPassword(s: String) {
    dataStore.edit { settings ->
      settings[stringPreferencesKey("encryption_password")] = s
    }
  }
}