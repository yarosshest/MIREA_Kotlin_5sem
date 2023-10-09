package com.example.work8.ui.authorization

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.work8.MainActivity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val loginStatus = viewModel.loginStatus.observeAsState()
        val context = LocalContext.current
        var login by remember { mutableStateOf("") }
        var pass by remember { mutableStateOf("") }


        if (loginStatus.value?.status == true) {

            navController.navigate("FindScreen")
        }

        if (loginStatus.value?.loginError != ""){
            loginStatus.value?.loginError?.let { Text(text = it) }
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
        Button(
            onClick = { viewModel.login(login, pass) }
        ) {
            Text(text = "Login")
        }
        Button(onClick = { navController.navigate("RegisterScreen")  }) {
            Text(text = "Register")
        }
        Button(onClick = { showToast(context ) }) {
            Text(text = "toast")
        }

    }


}

@SuppressLint("SuspiciousIndentation")
fun showToast(context:Context){
    val uploadRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
    val workManager = WorkManager.getInstance(context)
    workManager.enqueue(uploadRequest)
}

class MyWorker(private val context : Context, params: WorkerParameters): Worker(context, params){
    override fun doWork(): Result {
        Looper.prepare()
        val toast = Toast.makeText(context, "login", Toast.LENGTH_SHORT)
        toast.show()
        Looper.loop()
        return Result.success()
    }
}