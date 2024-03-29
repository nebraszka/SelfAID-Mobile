package nebraszka.selfaid.ui.adapters.exercises

import android.view.View
import androidx.lifecycle.LifecycleOwner
import nebraszka.selfaid.data.local.entities.ExerciseEntity
import nebraszka.selfaid.databinding.RowScaleQuestionBinding
import nebraszka.selfaid.ui.tools.SeekBarDescriptionChanger

class ScaleQuestionViewHolder(
    view: View, owner: LifecycleOwner, exercise: ExerciseEntity
) : ExerciseViewHolderBinder(view, owner, exercise) {

    private var _binding: RowScaleQuestionBinding? = null
    private val binding get() = _binding!!

    private fun bindProgressInfo() {
        _binding = RowScaleQuestionBinding.bind(view)
        val answer = binding.tvScaleAnswer
        binding.scaleQuestionRow.setOnSeekBarChangeListener(
            SeekBarDescriptionChanger(binding.scaleQuestionRow, answer)
        )
    }

    override fun bind() {
        bindProgressInfo()
        bindTopicAndId()
    }
}