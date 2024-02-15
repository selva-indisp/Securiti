package com.indisp.securiti.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.INSTANCE: DataStore<Preferences> by preferencesDataStore(name = "securiti")

class Storage(private val context: Context) {
    suspend fun save(key: String, value: String) {
        context.INSTANCE.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    suspend fun get(key: String): String? {
        return context.INSTANCE.data.map { it[stringPreferencesKey(key)] }.first()
    }
}