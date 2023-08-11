package com.digital.yazman.ah.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreLightDarkData(private val context: Context) {

    // to make sure there is only one instance
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Dark")
        val DARK = booleanPreferencesKey("DARK")
    }

    // to get name
    val getDark:Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DARK]?: false
        }

    // to save the name
    suspend fun setDark(dark:Boolean){
        context.dataStore.edit {preferences->
            preferences[DARK] = dark
        }
    }
}