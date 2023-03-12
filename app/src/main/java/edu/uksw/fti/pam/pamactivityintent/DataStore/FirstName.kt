package edu.uksw.fti.pam.pamactivityintent.DataStore
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class FirstName(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("FirstName")
        val USER_FIRST_KEY = stringPreferencesKey("firstname")

    }

    val getFirst: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_FIRST_KEY] ?: ""
        }

    suspend fun saveFirst(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_FIRST_KEY] = name
        }
    }
}