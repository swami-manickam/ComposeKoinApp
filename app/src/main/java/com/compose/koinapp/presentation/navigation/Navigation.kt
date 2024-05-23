package com.compose.koinapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.compose.koinapp.presentation.ui.HomeScreen
import com.compose.koinapp.presentation.ui.MovieDetailScreen
import com.compose.koinapp.presentation.viewmodel.MovieViewModel
import com.compose.koinapp.utils.AppRoute
import com.compose.koinapp.utils.Routes
import org.koin.androidx.compose.koinViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val movieViewModel: MovieViewModel = koinViewModel()

    NavHost(navController = navController, startDestination = AppRoute.HomeScreen.route) {


        composable(AppRoute.BannerScreen.route) {

        }

        composable(AppRoute.HomeScreen.route) {
            HomeScreen(navigation = navController, mainViewModel = movieViewModel)
        }

        composable("AppRoute.HomeScreen.route/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {

        }


    }
}

@Composable
fun AppNavigation(){

    val navController = rememberNavController()
    val movieViewModel:MovieViewModel = koinViewModel()


    NavHost(navController = navController, startDestination = Routes.LIST_SCREEN){

        composable(Routes.LIST_SCREEN){
            HomeScreen(navigation = navController, mainViewModel = movieViewModel)
        }
        composable(Routes.DETAIL_SCREEN, arguments = listOf(navArgument("idValue"){
            type = NavType.IntType
        })){backStackEntry  ->
            backStackEntry.arguments?.getInt(Routes.Values.IDVALUE,0)?.let {
                MovieDetailScreen(navController,movieViewModel,
                    it
                )
            }
        }

    }


}
