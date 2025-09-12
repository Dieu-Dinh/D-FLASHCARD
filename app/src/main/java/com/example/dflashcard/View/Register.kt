package com.example.dflashcard.View
import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dflashcard.Navigation.Screen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import com.example.dflashcard.ViewModel.RegisterVM
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun Register(navController: NavController, viewModel: RegisterVM = viewModel()) {
    var username by remember {mutableStateOf("")}
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

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
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxSize().padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email= it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxSize().padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxSize().padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { username = it },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxSize().padding(8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if(password == confirmPassword && username.isNotBlank() && email.isNotBlank()){
                    navController.navigate(Screen.Login.route){
                        popUpTo(Screen.Register.route){inclusive = true}
                    }
                }
                else {
                    errorMessage = "Registration failed. Please check your inputs."
                }
            }
        ) {
            Text(text = "Register")
        }
        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it, color = androidx.compose.ui.graphics.Color.Red)
        }
    }
}