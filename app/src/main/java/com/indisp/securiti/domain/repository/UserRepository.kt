package com.indisp.securiti.domain.repository

import com.indisp.securiti.domain.model.UserProfile

interface UserRepository {
    suspend fun saveProfile(profile: UserProfile): Boolean
    suspend fun getSavedProfile(): UserProfile?
}