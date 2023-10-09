package com.example.work8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
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

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MIREA_Kotlin_5semTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "LoginScreen"
                    ) {
                        composable("LoginScreen") {
                            val viewModel = hiltViewModel<LoginViewModel>()
                            LoginScreen(navController = navController, viewModel= viewModel)
                        }
                        composable("RegisterScreen") {
                            val viewModel = hiltViewModel<RegisterViewModel>()
                            RegisterScreen(navController = navController, viewModel= viewModel)
                        }
                        composable("FindScreen") {
                            val viewModel = hiltViewModel<FindViewModel>()
                            FindScreen(navController = navController, viewModel= viewModel)
                        }
                        composable("DbScreen") {
                            val viewModel = hiltViewModel<DBViewViewModel>()
                            DbScreen(navController = navController, viewModel= viewModel)
                        }
                    }
                }
            }
        }
    }
}