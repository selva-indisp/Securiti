package com.indisp.securiti.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class AgeGroup(val groupName: String) {
    SENIOR_CITIZEN("Senior"),
    CHILD("Child"),
    ADULT("Adult")
}