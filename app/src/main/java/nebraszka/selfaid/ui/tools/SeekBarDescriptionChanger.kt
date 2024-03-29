package nebraszka.selfaid.ui.tools

import android.widget.SeekBar
import android.widget.TextView
import nebraszka.selfaid.R

class SeekBarDescriptionChanger(sb: SeekBar, private var tvDescription: TextView) :
    SeekBar.OnSeekBarChangeListener {

    init {
        tvDescription.text = tvDescription.context.resources.getString(
            R.string.Progress_Scale_Question, sb.progress, sb.max
        )
    }

    override fun onProgressChanged(sb: SeekBar, progress: Int, fromUser: Boolean) {
        tvDescription.text = tvDescription.context.resources.getString(
            R.string.Progress_Scale_Question, progress, sb.max
        )
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }
}