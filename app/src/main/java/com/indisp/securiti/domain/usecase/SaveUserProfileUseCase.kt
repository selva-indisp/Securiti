package com.indisp.securiti.domain.usecase

import com.indisp.securiti.domain.model.UserProfile
import com.indisp.securiti.domain.repository.UserRepository

class SaveUserProfileUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(profile: UserProfile): Boolean {
        return userRepository.saveProfile(profile)
    }
}