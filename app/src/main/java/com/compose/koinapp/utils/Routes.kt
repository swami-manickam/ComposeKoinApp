package com.compose.koinapp.utils

object Routes {

    const val LIST_SCREEN = "listScreen"
    const val DETAIL_SCREEN = "detailScreen/{${Values.IDVALUE}}"

    fun getSecondScreenPath(idValue: Int?): String =
        if (idValue != null) "detailScreen/$idValue" else "detailScreen/Empty"

    object Values {
        const val IDVALUE = "idValue"
    }

}


sealed class AppRoute(val route: String){
    data object HomeScreen: AppRoute("home")
    data object BannerScreen:AppRoute("banner")
    data object DetailScreen :AppRoute("detail")
}