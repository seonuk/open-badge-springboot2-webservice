package edu.dongguk.openBadge.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginViewModel(
    @JsonProperty("userName") val userName: String,
    @JsonProperty("password") val password: String
)
