package dev.tberghuis.btmacrokb.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.tberghuis.btmacrokb.nav.Route
import androidx.navigation.NavDestination.Companion.hasRoute
import dev.tberghuis.btmacrokb.util.logd

@Composable
fun BottomBar() {
  val nav = LocalNavController.current
  val navBackStackEntry = nav.currentBackStackEntryAsState().value
  val hierarchy = navBackStackEntry?.destination?.hierarchy

  fun barItemClick(route: Route) {
    // https://medium.com/@ianhlake/theres-a-hasroute-extension-method-on-navdestination-specifically-for-exactly-this-b6f744c6f734
    // whats the alternative than using restricted api?
    // https://github.com/android/compose-samples/issues/1483
    if (navBackStackEntry?.destination?.hierarchy?.any {
        it.hasRoute(route::class)
      } == false) {
      logd("navigating $route")
      nav.navigate(route)
    }
  }

  NavigationBar(
    modifier = Modifier,
  ) {
    NavigationBarItem(
      selected = hierarchy?.any {
        it.hasRoute<Route.Connection>()
      } == true,
      onClick = {
        barItemClick(Route.Connection)
      },
      icon = {
        Icon(Icons.Filled.Bluetooth, contentDescription = "connection")
      },
      modifier = Modifier,
      label = {
        Text("Connection")
      },
    )
    NavigationBarItem(
      selected = hierarchy?.any {
        it.hasRoute<Route.Macro>()
      } == true,
      onClick = {
        barItemClick(Route.Macro)
      },
      icon = {
        Icon(Icons.AutoMirrored.Filled.Article, contentDescription = "scripts")
      },
      modifier = Modifier,
      label = {
        // use the term "Scripts" until modifier key combos supported
        // then use the term "Macros"
        Text("Scripts")
      },
    )
  }
}