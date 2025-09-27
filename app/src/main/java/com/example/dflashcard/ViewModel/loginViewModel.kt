package com.example.dflashcard.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

sealed class LoginState {
    object IDLE : LoginState()
    object LOADING : LoginState()
    data class SUCCESS(val message: String) : LoginState()
    data class ERROR(val error: String) : LoginState()
}

class LoginViewModel: ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    var loginState = mutableStateOf<LoginState>(LoginState.IDLE)
        private set

    // state for username and password
   var username = mutableStateOf("")
       private set

   var password = mutableStateOf("")
         private set

    // function to update username
   fun onUsernameChange(newUsername: String){
        username.value = newUsername
    }

   fun onPasswordChange(newPassword: String) {
        password.value = newPassword
    }


    fun login() {
        viewModelScope.launch {
            if(username.value.isBlank() || password.value.isBlank()) {
                loginState.value = LoginState.ERROR("Please fill in all fields")
                return@launch
            }
            loginState.value = LoginState.LOADING

            // Tim email tu username trong Firestore
            db.collection("users")
                .whereEqualTo("username", username.value)
                .get()
                .addOnSuccessListener { docs ->
                    if (!docs.isEmpty){
                        val email = docs.first().getString("email")?: ""
                        // Dang nhap voi email va password
                        auth.signInWithEmailAndPassword(email, password.value)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful){
                                    loginState.value = LoginState.SUCCESS("Login successful")
                                } else {
                                    loginState.value = LoginState.ERROR(task.exception?.message ?: "Login failed")
                                }

                            }
                    }
                    else{
                        loginState.value = LoginState.ERROR("User not found")
                    }
                }
                .addOnFailureListener { exception ->
                    loginState.value = LoginState.ERROR(exception.message ?: "Error fetching user data")
                }
        }
    }



}