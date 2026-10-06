package com.example.shophub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shophub.ui.screens.*
import com.example.shophub.ui.theme.ShopHubTheme

sealed class Screen(val route: String, val title: String, val icon: ImageVector? = null) {
    object Login : Screen("login", "Login")
    object Home : Screen("home", "Início", Icons.Default.Home)
    object Categories : Screen("categories", "Categorias", Icons.Default.Category)
    object Search : Screen("search", "Buscar", Icons.Default.Search)
    object Cart : Screen("cart", "Carrinho", Icons.Default.ShoppingCart)
    object Profile : Screen("profile", "Perfil", Icons.Default.Person)
    object MyOrders : Screen("my_orders", "Minhas Compras")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopHubTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarItems = listOf(
        Screen.Home,
        Screen.Categories,
        Screen.Search,
        Screen.Cart,
        Screen.Profile
    )

    val showBottomBar = currentRoute in bottomBarItems.map { it.route }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomBarItems.forEach { screen ->
                        NavigationBarItem(
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                screen.icon?.let {
                                    Icon(imageVector = it, contentDescription = screen.title)
                                }
                            },
                            label = { Text(screen.title) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                })
            }
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Categories.route) {
                CategoriesScreen()
            }
            composable(Screen.Search.route) {
                SearchScreen()
            }
            composable(Screen.Cart.route) {
                CartScreen(onCheckoutSuccess = {
                    navController.navigate(Screen.MyOrders.route)
                })
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    onLogout = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    onNavigateToOrders = {
                        navController.navigate(Screen.MyOrders.route)
                    }
                )
            }
            composable(Screen.MyOrders.route) {
                MyOrdersScreen()
            }
        }
    }
}