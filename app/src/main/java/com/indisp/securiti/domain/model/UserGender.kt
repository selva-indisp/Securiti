package com.indisp.securiti.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class Gender(val genderType: String) {
    MALE("Male"),
    FEMALE("Female")
}