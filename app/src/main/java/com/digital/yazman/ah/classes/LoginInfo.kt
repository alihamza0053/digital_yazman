package com.digital.yazman.ah.classes

data class LoginInfo(
    val id: String,
    val name: String,
    val address: String,
    val phone: String,
    val email: String,
    val date: String,
    val notify: String,
    val verify: Int,
    val accountType: String,
)
