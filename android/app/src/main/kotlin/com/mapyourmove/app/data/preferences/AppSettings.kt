package com.mapyourmove.app.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

object AppSettings {
    private lateinit var context: Context
    
    fun initialize(appContext: Context) {
        context = appContext
    }
    
    private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    private val USER_ID = stringPreferencesKey("user_id")
    private val USER_EMAIL = stringPreferencesKey("user_email")
    private val PREFERRED_LANGUAGE = stringPreferencesKey("preferred_language")
    private val DARK_MODE = booleanPreferencesKey("dark_mode")
    private val AUTH_TOKEN = stringPreferencesKey("auth_token")
    
    val isLoggedInFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[IS_LOGGED_IN] ?: false
    }
    
    val userIdFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_ID]
    }
    
    val userEmailFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_EMAIL]
    }
    
    val preferredLanguageFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PREFERRED_LANGUAGE] ?: "English"
    }
}
