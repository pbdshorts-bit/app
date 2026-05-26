package com.mapyourmove.app.domain.models

import java.util.Date

data class User(
    val id: String,
    val fullName: String,
    val email: String,
    val profileImageUrl: String? = null,
    val preferredLanguage: String = "English",
    val createdAt: Date = Date(),
    val isVerified: Boolean = false,
    val authProvider: AuthProvider = AuthProvider.EMAIL
)

enum class AuthProvider {
    EMAIL,
    GOOGLE,
    APPLE
}
