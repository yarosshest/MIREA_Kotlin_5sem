package com.example.work8

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.work8.authorization.LoginViewModel
import com.example.work8.authorization.RegisterViewModel
import com.example.work8.db.DBViewViewModel
import com.example.work8.find.FindViewModel
import com.example.work8.ui.find.FindScreen
import com.example.work8.ui.authorization.LoginScreen
import com.example.work8.ui.authorization.RegisterScreen
import com.example.work8.ui.db.DbScreen
import com.example.work8.ui.theme.MIREA_Kotlin_5semTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Create

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MIREA_Kotlin_5semTheme {
                var title by remember { mutableStateOf("") }
                Scaffold(
                    drawerContent ={
                        Column {
                            IconButton(onClick = { navController.navigate("DbScreen") }) {
                                title= "DbScreen"
                                Icon(Icons.AutoMirrored.Outlined.List, contentDescription = "Localized description")
                            }
                            IconButton(onClick = { navController.navigate("FindScreen")}) {
                                title= "FindScreen"
                                Icon(Icons.Outlined.Search, contentDescription = "Localized description")
                            }
                            IconButton(onClick = { navController.navigate("LoginScreen")}) {
                                title= "LoginScreen"
                                Icon(Icons.Outlined.AccountBox, contentDescription = "Localized description")
                            }
                            IconButton(onClick = { navController.navigate("RegisterScreen")}) {
                                title= "RegisterScreen"
                                Icon(Icons.Outlined.Create, contentDescription = "Localized description")
                            }
                        }
                    },
                    topBar = {
                        TopAppBar(
                            title =  { Text(title) }
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.primary,
                        ) {
                            Row {
                                IconButton(onClick = { navController.navigate("DbScreen") }) {
                                    title= "DbScreen"
                                    Icon(Icons.AutoMirrored.Outlined.List, contentDescription = "Localized description")
                                }
                                IconButton(onClick = { navController.navigate("FindScreen")}) {
                                    title= "FindScreen"
                                    Icon(Icons.Outlined.Search, contentDescription = "Localized description")
                                }
                                IconButton(onClick = { navController.navigate("LoginScreen")}) {
                                    title= "LoginScreen"
                                    Icon(Icons.Outlined.AccountBox, contentDescription = "Localized description")
                                }
                                IconButton(onClick = { navController.navigate("RegisterScreen")}) {
                                    title= "RegisterScreen"
                                    Icon(Icons.Outlined.Create, contentDescription = "Localized description")
                                }
                            }
                        }
                    },
                    content = {innerPadding ->
                        NavHost(
                            modifier = Modifier
                                .padding(innerPadding),
                            navController = navController,
                            startDestination = "LoginScreen"
                        ) {
                            composable("LoginScreen") {

                                title= "LoginScreen"
                                val viewModel = hiltViewModel<LoginViewModel>()
                                LoginScreen(navController = navController, viewModel= viewModel)
                            }
                            composable("RegisterScreen") {
                                title= "RegisterScreen"
                                val viewModel = hiltViewModel<RegisterViewModel>()
                                RegisterScreen(navController = navController, viewModel= viewModel)
                            }
                            composable("FindScreen") {
                                title= "FindScreen"
                                val viewModel = hiltViewModel<FindViewModel>()
                                FindScreen(navController = navController, viewModel= viewModel)
                            }
                            composable("DbScreen") {
                                title= "DbScreen"
                                val viewModel = hiltViewModel<DBViewViewModel>()
                                DbScreen(navController = navController, viewModel= viewModel)
                            }
                        }
                    }
                )
            }
        }
    }

}