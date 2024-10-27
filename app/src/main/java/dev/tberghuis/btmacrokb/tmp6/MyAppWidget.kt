package dev.tberghuis.btmacrokb.tmp6

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.padding
import androidx.glance.text.Text

class MyAppWidget : GlanceAppWidget() {

  override suspend fun provideGlance(context: Context, id: GlanceId) {

    // In this method, load data needed to render the AppWidget.
    // Use `withContext` to switch to another thread for long running
    // operations.

    provideContent {
      MyContent()
    }
  }


  @Composable
  private fun MyContent() {
    GlanceTheme {
      Column(
        modifier = GlanceModifier
          .background(GlanceTheme.colors.surface)
          .cornerRadius(24.dp)
          .padding(10.dp)
      ) {
        Text("Hello World")
      }
    }
  }


}