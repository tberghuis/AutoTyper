package dev.tberghuis.btmacrokb.nav

import kotlinx.serialization.Serializable

sealed interface Route {
  @Serializable
  data object Permission : Route

  @Serializable
  data object Connection : Route

  @Serializable
  data object Macro : Route

  @Serializable
  data object MacroList : Route

  @Serializable
  data object Settings : Route

  // use 0 to indicate new macro
  // or should I use separate route?? NewMacro nah
  @Serializable
  data class MacroDetail(val id: Long) : Route
}