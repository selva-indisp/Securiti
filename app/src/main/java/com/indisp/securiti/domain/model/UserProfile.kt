package com.indisp.securiti.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(val name: String, val gender: Gender, val category: AgeGroup)