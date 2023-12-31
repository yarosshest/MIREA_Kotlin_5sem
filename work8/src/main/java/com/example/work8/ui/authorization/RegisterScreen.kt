package com.example.work8.ui.authorization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.work8.authorization.LoginViewModel
import com.example.work8.authorization.RegisterViewModel
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val registerStatus = viewModel.registerStatus.observeAsState()
//        var login: String = ""
        var login by remember { mutableStateOf("") }
        var pass by remember { mutableStateOf("") }
//        var pass: String = ""

        if (registerStatus.value?.status == true) {
            navController.navigate("FindScreen")
        }

        if (registerStatus.value?.registerError != ""){
            registerStatus.value?.registerError?.let { Text(text = it) }
        }

        TextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("Login") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Password") },
            singleLine = true
        )

        Button(onClick = { viewModel.register(login, pass)  }) {
            Text(text = "Register")
        }

    }
}
