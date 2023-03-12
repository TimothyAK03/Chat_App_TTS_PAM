package edu.uksw.fti.pam.pamactivityintent.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LastName (private val context: Context) {
    companion object {
        private val Context.storeData: DataStore<Preferences> by preferencesDataStore("LastName")
        val USER_LAST_KEY = stringPreferencesKey("lastname")
    }

    val getLast: Flow<String?> = context.storeData.data
        .map { preferences ->
            preferences[USER_LAST_KEY] ?: ""
        }

    suspend fun saveLast(name: String) {
        context.storeData.edit { preferences ->
            preferences[USER_LAST_KEY] = name
        }
    }
}
