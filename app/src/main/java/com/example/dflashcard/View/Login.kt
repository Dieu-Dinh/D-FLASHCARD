package com.example.dflashcard.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.dflashcard.R
import com.example.dflashcard.ViewModel.LoginViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.dflashcard.ViewModel.LoginState
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(), navController: NavHostController) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // đọc state từ ViewModel
    val username by viewModel.username
    val password by viewModel.password
    val loginState = viewModel.loginState.value

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply padding from Scaffold
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Flash_Card",
                    modifier = Modifier.size(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
// Username Input Field
            OutlinedTextField(
                value = username,
                onValueChange = { viewModel.onUsernameChange(it) },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

// Username Input Field
            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))


            // Login Button
            Button(
                onClick = {
                    viewModel.login()
                          },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button (
                onClick = {
                    navController.navigate("Register")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Register")
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Or login with",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),

            ){
                Button(
                    onClick = { /* Handle Google login */ },
                    modifier = Modifier
                        .padding(8.dp)
                        .size(width = 200.dp, height = 50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gg_logo),
                        contentDescription = "Google Logo",
                        modifier = Modifier.size(24.dp)
                    )

                }

                Button(
                    onClick = { /* Handle Facebook login */ },
                    modifier = Modifier
                        .padding(8.dp)
                        .size(width = 200.dp, height = 50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fb_logo),
                        contentDescription = "Facebook Logo",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            if (loginState == LoginState.LOADING) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f)), // nền mờ
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginState.SUCCESS -> {
                scope.launch { snackbarHostState.showSnackbar(loginState.message)}
                navController.navigate("Home") {
                    popUpTo("Login") { inclusive = true }
                }
            }
            is LoginState.ERROR -> {
                scope.launch { snackbarHostState.showSnackbar(loginState.error) }
            }
            LoginState.LOADING -> {
                // Do nothing, loading indicator is already shown
            }
            else -> {
                // Do nothing for IDLE state
            }
        }
    }
}