package com.example.dflashcard.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    // state for username and password
   var username = mutableStateOf("")
       private set

   var password = mutableStateOf("")
         private set
    // function to update username
   fun onUsernameChange(newUsername: String){
        username.value = newUsername
    }

   fun onPassworodChaange(newPassword: String) {
        password.value = newPassword
    }

   fun login(): Boolean {
        return username.value == "user" && password.value == "password"
    }


}