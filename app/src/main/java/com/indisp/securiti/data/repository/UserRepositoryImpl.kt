package com.indisp.securiti.data.repository

import android.util.Log
import com.indisp.securiti.domain.model.UserProfile
import com.indisp.securiti.domain.repository.UserRepository
import com.indisp.securiti.storage.Storage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserRepositoryImpl(
    private val storage: Storage
) : UserRepository {

    private companion object {
        const val USER_PROFILE = "user_profile"
    }

    override suspend fun saveProfile(profile: UserProfile): Boolean {
        return try {
            val userProfileString = Json.encodeToString(profile)
            Log.d("PROD", "saveProfile: $userProfileString")
            storage.save(USER_PROFILE, userProfileString)
            true
        } catch (ex: Throwable) {
            // Log the exception
            ex.printStackTrace()
            false
        }
    }

    override suspend fun getSavedProfile(): UserProfile? {
        val userProfileString = storage.get(USER_PROFILE)
        if (userProfileString.isNullOrEmpty())
            return null
        return try {
            Json.decodeFromString<UserProfile>(userProfileString).also {
                Log.d("PROD", "getSavedProfile: $it")
            }
        } catch (ex: Throwable) {
            // Log error
            ex.printStackTrace()
            null
        }
    }
}