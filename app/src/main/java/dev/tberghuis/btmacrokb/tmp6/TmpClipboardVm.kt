package dev.tberghuis.btmacrokb.tmp6

import android.app.Application
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import androidx.lifecycle.AndroidViewModel
import dev.tberghuis.btmacrokb.util.logd

class TmpClipboardVm(
  private val application: Application,
) : AndroidViewModel(application) {
  val willitblend = "will it blend"

  fun readClipboard() {
    val clipBoardManager = application.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    val copiedString = clipBoardManager.primaryClip?.getItemAt(0)?.text?.toString()
    logd("readClipboard copiedString $copiedString")
  }
}