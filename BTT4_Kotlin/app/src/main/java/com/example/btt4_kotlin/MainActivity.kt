package com.example.btt4_kotlin

import com.example.btt4_kotlin.ui.theme.BTT4_KotlinTheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BTT4_KotlinTheme {
                val navHostController = rememberNavController()
                NavHost(navHostController, startDestination = "screen4"){
                    //bai1 quan ly sach va sinh vien
                    composable("screen4") { baitap4(navHostController) }

                    //bai2 truyen du lieu qua nav
                    composable("navDataFlow") { DataFlowNavigation(navHostController) }
                    composable("navDataFlow2/{email}",
                        arguments = listOf(navArgument("email") {
                            type = NavType.StringType
                        })
                    ){ backStackEntry ->
                        val email = backStackEntry.arguments?.getString("email")
                        if (email != null) {
                            DataFlowNavigation2(email,navHostController)
                        }
                    }

                    composable("navDataFlow3/{email}/{otp}",
                        arguments = listOf(
                            navArgument("otp") {
                                type = NavType.StringType
                            }
                            ,navArgument("email") {
                                type = NavType.StringType
                            }
                        )
                    ) {backStackEntry ->
                        val email = backStackEntry.arguments?.getString("email")
                        val otp = backStackEntry.arguments?.getString("otp")
                        if (email!=null && otp != null) {
                            DataFlowNavigation3(email,otp, navHostController)
                        }
                    }

                    composable("navDataFlow4/{email}/{otp}/{pass}",
                        arguments = listOf(navArgument("pass") {
                            type = NavType.StringType
                        })
                    ) {backStackEntry ->
                        val email = backStackEntry.arguments?.getString("email")
                        val otp = backStackEntry.arguments?.getString("otp")
                        val pass = backStackEntry.arguments?.getString("pass")
                        DataFlowNavigation4(email,otp,pass, navHostController)

                    }

                }
            }
        }
    }
}




