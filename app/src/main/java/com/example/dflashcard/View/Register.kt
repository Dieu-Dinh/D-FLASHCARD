package com.example.dflashcard.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dflashcard.Navigation.Screen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dflashcard.ViewModel.RegisterVM
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.example.dflashcard.ViewModel.RegisterState


@Composable
fun Register(navController: NavController, viewModel: RegisterVM = viewModel()) {
    val username by viewModel.username
    val email by viewModel.email
    val password by viewModel.password
    val confirmPassword by viewModel.confirmPassword
    val registerState by viewModel.registerState

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ){
        Image(
            painter = androidx.compose.ui.res.painterResource(id = com.example.dflashcard.R.drawable.logo),
            contentDescription = "Logo Flash_Card",
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { viewModel.onUsernameChange(it) },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { viewModel.onConfirmPasswordChange(it) },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.register()
            }
        ) {
            Text(text = "Register")
        }

        Spacer(modifier = Modifier.height(24.dp))

    }

    // Xu ly trang thai dang ki
    when(val currentState = registerState){
        is RegisterState.SUCCESS -> {
            Text(text = currentState.message, color = Color.Green)

            navController.navigate(Screen.Login.route){
                popUpTo(Screen.Register.route) {inclusive = true}
            }
        }
        is RegisterState.ERROR -> {
            Text(text = currentState.error, color = Color.Red)
        }
        else -> { /* Do nothing */ }
    }
}