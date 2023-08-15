package com.digital.yazman.ah.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserInfo(private val context: Context) {
    companion object{
        private val Context.dataStore:DataStore<Preferences> by preferencesDataStore("UserInfo")
        val ID = stringPreferencesKey("ID")
        val NAME = stringPreferencesKey("NAME")
        val EMAIL = stringPreferencesKey("EMAIL")
        val ADDRESS = stringPreferencesKey("ADDRESS")
        val PHONE = stringPreferencesKey("PHONE")
        val NOTIFY = stringPreferencesKey("NOTIFY")
        val VERIFY = stringPreferencesKey("VERIFY")
    }

    // to get id
    val getId: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[UserInfo.ID]?: ""
        }

    // to save the id
    suspend fun setId(id:String){
        context.dataStore.edit {preferences->
            preferences[UserInfo.ID] = id
        }
    }


    // to get name
    val getName: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[UserInfo.NAME]?: ""
        }

    // to save the name
    suspend fun setName(name:String){
        context.dataStore.edit {preferences->
            preferences[UserInfo.NAME] = name
        }
    }

    // to get email
    val getEmail: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[UserInfo.EMAIL]?: ""
        }

    // to save the email
    suspend fun setEmail(email:String){
        context.dataStore.edit {preferences->
            preferences[UserInfo.EMAIL] = email
        }
    }


    // to get address
    val getAddress: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[UserInfo.ADDRESS]?: ""
        }

    // to save the address
    suspend fun setAddress(address:String){
        context.dataStore.edit {preferences->
            preferences[UserInfo.ADDRESS] = address
        }
    }

    // to get phone
    val getPhone: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[UserInfo.PHONE]?: ""
        }

    // to save the phone
    suspend fun setPhone(phone:String){
        context.dataStore.edit {preferences->
            preferences[UserInfo.PHONE] = phone
        }
    }

    // to get notify
    val getNotify: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[UserInfo.NOTIFY]?: ""
        }

    // to save the notify
    suspend fun setNotify(notify:String){
        context.dataStore.edit {preferences->
            preferences[UserInfo.NOTIFY] = notify
        }
    }

    // to get verify
    val getVerify: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[UserInfo.VERIFY]?: ""
        }

    // to save the verify
    suspend fun setVerify(verify:String){
        context.dataStore.edit {preferences->
            preferences[UserInfo.VERIFY] = verify
        }
    }


}