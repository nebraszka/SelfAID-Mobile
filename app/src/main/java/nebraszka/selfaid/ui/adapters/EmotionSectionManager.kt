package nebraszka.selfaid.ui.adapters

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.transition.R
import nebraszka.selfaid.data.domain.model.Emotion

class EmotionSectionManager {

    companion object {
        fun initiateEmotionSpinner(context: Context, emotions: List<Emotion>, spinner: Spinner) {
            val emotionsNames = emotions.map { it.emotion }
            val adapter = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, emotionsNames)
            spinner.adapter = adapter
        }
    }
}