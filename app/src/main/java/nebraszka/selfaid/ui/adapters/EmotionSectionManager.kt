package nebraszka.selfaid.ui.adapters

import android.content.Context
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.transition.R
import nebraszka.selfaid.data.entities.Emotion
import nebraszka.selfaid.ui.fragments.EmotionDescription

class EmotionSectionManager {

    companion object {
        fun emotionInfoIntent(context: Context, emotion: Emotion): Intent {
            val intent = Intent(context, EmotionDescription::class.java)
            intent.putExtra("EXTRA_EMOTION_NAME", emotion.emotion)
            intent.putExtra("EXTRA_EMOTION_DESCRIPTION", emotion.description)
            return intent
        }

        fun initiateEmotionSpinner(context: Context, emotions: List<Emotion>, spinner: Spinner) {
            val emotionsNames = emotions.map { it.emotion }
            val adapter = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, emotionsNames)
            spinner.adapter = adapter
        }
    }
}