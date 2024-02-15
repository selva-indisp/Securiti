package com.indisp.securiti.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.indisp.securiti.domain.model.AgeGroup
import com.indisp.securiti.domain.model.Gender
import com.indisp.securiti.domain.model.UserProfile
import com.indisp.securiti.domain.usecase.RetrieveSavedUserProfileUseCase
import com.indisp.securiti.domain.usecase.SaveUserProfileUseCase
import kotlinx.coroutines.launch

class UserProfileViewModel(
    private val saveUserProfileUseCase: SaveUserProfileUseCase,
    private val retrieveSavedUserProfileUseCase: RetrieveSavedUserProfileUseCase
) : BaseViewModel<State>() {

    init {
        viewModelScope.launch {
            retrieveSavedProfile()
        }
    }
    fun onSubmitClicked() {
        viewModelScope.launch {
            val userProfile = UserProfile(
                state.value.selectedName!!,
                state.value.selectedGender!!,
                state.value.selectedAgeGroup!!
            )
            saveUserProfileUseCase(userProfile)
        }
    }

    fun onAgeGroupSelected(ageGroup: AgeGroup) {
        update {
            copy(
                selectedAgeGroup = ageGroup
            )
        }
    }

    fun onGenderSelected(gender: Gender) {
        update {
            copy(
                selectedGender = gender
            )
        }
    }

    fun onNameChanged(newName: String) {
        Log.d("PROD", "onNameChanged: $newName")
        update {
            copy(
                selectedName = newName
            )
        }
    }

    private fun retrieveSavedProfile() {
        viewModelScope.launch {
            val userProfile = retrieveSavedUserProfileUseCase()
            if (userProfile != null) {
                update {
                    copy(
                        selectedName = userProfile.name,
                        selectedGender = userProfile.gender,
                        selectedAgeGroup = userProfile.category
                    )
                }
            }
        }
    }

    override fun getInitialState(): State {
        val ageGroups = listOf(AgeGroup.ADULT, AgeGroup.CHILD, AgeGroup.SENIOR_CITIZEN)
        val genders = listOf(Gender.MALE, Gender.FEMALE)
        return State("", null, null, ageGroups, genders)
    }
}

data class State(
    val selectedName: String = "",
    val selectedGender: Gender?,
    val selectedAgeGroup: AgeGroup?,
    val ageGroups: List<AgeGroup>,
    val genders: List<Gender>
)

class UserProfileViewModelFactory(
    private val saveUserProfileUseCase: SaveUserProfileUseCase,
    private val retrieveSavedUserProfileUseCase: RetrieveSavedUserProfileUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserProfileViewModel(saveUserProfileUseCase, retrieveSavedUserProfileUseCase) as T
    }
}