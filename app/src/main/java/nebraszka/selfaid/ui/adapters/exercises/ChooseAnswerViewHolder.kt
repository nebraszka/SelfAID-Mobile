package nebraszka.selfaid.ui.adapters.exercises

import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import nebraszka.selfaid.data.local.SelfAIDDatabase
import nebraszka.selfaid.data.local.entities.AnswerSuggestionEntity
import nebraszka.selfaid.data.local.entities.ExerciseEntity
import nebraszka.selfaid.databinding.RowChooseOptionBinding

class ChooseAnswerViewHolder(
    view: View, owner: LifecycleOwner, exercise: ExerciseEntity, private val multipleChoice: Boolean
) : ExerciseViewHolderBinder(view, owner, exercise) {

    private var _binding: RowChooseOptionBinding? = null
    private val binding get() = _binding!!

    private val database = SelfAIDDatabase.getDatabase(view.context)

    private fun bindSuggestedAnswers() {
        _binding = RowChooseOptionBinding.bind(view)
        val answerSuggestions = database.answerSuggestionDao()
            .getAllAnswerSuggestions(exercise.id).asLiveData()

        answerSuggestions.observe(owner) {
            attachAnswers(it)
        }
    }

    private fun attachAnswers(answerSuggestions: List<AnswerSuggestionEntity>) {
        if (multipleChoice) {
            for (answer in answerSuggestions) {
                val checkBox = CheckBox(view.context)
                checkBox.text = answer.answer
                checkBox.id = answer.id
                binding.llAnswerSuggestions.addView(checkBox)

            }
        } else {
            val radioGroup = RadioGroup(view.context)
            binding.llAnswerSuggestions.addView(radioGroup)

            for (answer in answerSuggestions) {
                val btn= RadioButton(view.context)
                btn.text = answer.answer
                btn.id = answer.id
                radioGroup.addView(btn)
            }
        }
    }

    override fun bind() {
        bindTopicAndId()
        bindSuggestedAnswers()
    }
}