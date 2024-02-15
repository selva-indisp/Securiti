package com.indisp.securiti.domain.usecase

import com.indisp.securiti.domain.model.UserProfile
import com.indisp.securiti.domain.repository.UserRepository

class RetrieveSavedUserProfileUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): UserProfile? {
        return userRepository.getSavedProfile()
    }
}