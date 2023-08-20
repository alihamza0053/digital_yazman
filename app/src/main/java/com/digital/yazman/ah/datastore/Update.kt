package com.digital.yazman.ah.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Update(private val context: Context) {
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Update")
        val TITLE = stringPreferencesKey("TITLE")
        val SHORTDES = stringPreferencesKey("SHORTDES")
        val VERSION = stringPreferencesKey("VERSION")
        val LINK = stringPreferencesKey("LINK")
    }

    // to get title
    val getTitle: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[Update.TITLE]?: ""
        }

    // to save the title
    suspend fun setTitle(title:String){
        context.dataStore.edit {preferences->
            preferences[Update.TITLE] = title
        }
    }
    // to get short description
    val getShortDes: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[Update.SHORTDES]?: ""
        }

    // to save the short description
    suspend fun setShortDes(shortDes:String){
        context.dataStore.edit {preferences->
            preferences[Update.SHORTDES] = shortDes
        }
    }
    // to get version
    val getVersion: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[Update.VERSION]?: ""
        }

    // to save the version
    suspend fun setVersion(version:String){
        context.dataStore.edit {preferences->
            preferences[Update.VERSION] = version
        }
    }
    // to get link
    val getLink: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[Update.LINK]?: ""
        }

    // to save the link
    suspend fun setLink(link:String){
        context.dataStore.edit {preferences->
            preferences[Update.LINK] = link
        }
    }
}