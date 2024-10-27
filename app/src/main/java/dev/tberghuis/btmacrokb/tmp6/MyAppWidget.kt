package dev.tberghuis.btmacrokb.tmp6

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.text.Text

class MyAppWidget : GlanceAppWidget() {

  override suspend fun provideGlance(context: Context, id: GlanceId) {

    // In this method, load data needed to render the AppWidget.
    // Use `withContext` to switch to another thread for long running
    // operations.

    provideContent {
      // create your AppWidget here
      Text("Hello World")
    }
  }


  @Composable
  private fun MyContent() {

  }


}