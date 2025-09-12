package com.example.dflashcard.ViewModel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dflashcard.View.Register
import kotlinx.coroutines.launch

sealed class RegisterState{
    object IDLE: RegisterState()
    data class SUCCESS(val message: String): RegisterState()
    data class ERROR(val error: String): RegisterState()
}


class RegisterVM: ViewModel() {
    var username = mutableStateOf("")
        private set
    var email = mutableStateOf("")
        private set
    var password = mutableStateOf("")
        private set
    var confirmPassword = mutableStateOf("")
        private set

    // trang thai dang ki
    var registerState = mutableStateOf<RegisterState>(RegisterState.IDLE)
        private set

    // ham update
    fun onUsernameChange(newUsername: String) {
        username.value = newUsername
    }
    fun onEmailChange(newEmail: String) {
        email.value = newEmail
    }
    fun onPasswordChange(newPassword: String) {
        password.value = newPassword
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        confirmPassword.value = newConfirmPassword
    }
    // ham dang ki
    fun register() {
        viewModelScope.launch {
            if(username.value.isBlank() || email.value.isBlank() || password.value.isBlank() || confirmPassword.value.isBlank()) {
                registerState.value = RegisterState.ERROR("Please fill in all fields")
                return@launch
            }

            if (password.value != confirmPassword.value) {
                registerState.value = RegisterState.ERROR("Passwords do not match")
                return@launch
            }

            if (email.value == "xxxxxxx@gmail.com"){
                registerState.value = RegisterState.ERROR("Email already in use")
            }
            else {
                registerState.value = RegisterState.SUCCESS("Registration successful")
            }
        }
    }

}