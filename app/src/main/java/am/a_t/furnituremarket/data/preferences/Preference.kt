package am.a_t.furnituremarket.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.core.remove
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.first

class Preference(context: Context) {
    private val dataStore = context.createDataStore("DataStoreAuthLogin")

    suspend fun saveUser(key: String, value: String) {
        dataStore.edit {
            it[preferencesKey<String>(key)] = value
        }
    }

    suspend fun readUser(key: String): String? = dataStore.data.first()[preferencesKey(key)]

    suspend fun removeUser(key: String) {
        dataStore.edit {
            it.remove(preferencesKey<String>(key))
        }
    }

    companion object {
        const val USER_LOGIN = "user_login"
    }

}