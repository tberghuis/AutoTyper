package dev.tberghuis.btmacrokb.nav

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import dev.tberghuis.btmacrokb.screens.ConnectionScreen
import dev.tberghuis.btmacrokb.screens.MacroListScreen
import dev.tberghuis.btmacrokb.composables.LocalNavController
import dev.tberghuis.btmacrokb.screens.PermissionScreen
import dev.tberghuis.btmacrokb.screens.MacroDetailScreen
import dev.tberghuis.btmacrokb.tmp7.SettingsScreen

val LocalSnackbarHostState = compositionLocalOf<SnackbarHostState> {
  error("CompositionLocal LocalSnackbarHostState not present")
}

@Composable
fun MyApp() {
  val nav = rememberNavController()
  val snackbarHostState = remember { SnackbarHostState() }
  CompositionLocalProvider(LocalNavController provides nav) {
    CompositionLocalProvider(LocalSnackbarHostState provides snackbarHostState) {
      NavHost(
        navController = nav,
        startDestination = Route.Permission,
        // https://developer.android.com/develop/ui/compose/layouts/insets
//        modifier = Modifier.safeDrawingPadding(),
      ) {
        composable<Route.Permission> { entry ->
          PermissionScreen()
        }
        composable<Route.Connection> { entry ->
          ConnectionScreen()
        }
        navigation<Route.Macro>(startDestination = Route.MacroList) {
          composable<Route.MacroList> {
            MacroListScreen()
          }
          composable<Route.MacroDetail> { backStackEntry ->
            MacroDetailScreen()
          }
        }
        composable<Route.Settings> { entry ->
          SettingsScreen()
        }
      }
    }
  }
}