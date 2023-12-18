package nebraszka.selfaid.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.RecyclerView
import nebraszka.selfaid.R
import nebraszka.selfaid.data.local.SelfAIDDatabase
import nebraszka.selfaid.data.local.entities.ExerciseEntity
import nebraszka.selfaid.databinding.RowAnswerBinding
import nebraszka.selfaid.ui.enums.ExerciseTypeEn

class AnswersViewHolder(private val binding: RowAnswerBinding, private val entryId: Int) :
    RecyclerView.ViewHolder(binding.root) {

    private val database = SelfAIDDatabase.getDatabase(itemView.context)

    companion object {
        fun createViewHolder(parent: ViewGroup, viewType: Int, entryId: Int): AnswersViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = RowAnswerBinding.inflate(inflater, parent, false)

            return AnswersViewHolder(binding, entryId)
        }
    }
    fun bind(exercise: ExerciseEntity, owner: LifecycleOwner) {
        binding.tvExerciseTopic.text = exercise.topic

        val responds = database.respondDao()
            .getRespondsForExercise(entryId, 1, exercise.id).asLiveData()

        val resources =  itemView.context.resources
        responds.observe(owner) {
            binding.tvAnswer.text =
                when (exercise.exerciseType) {

                    ExerciseTypeEn.VIEW_QUESTION.id -> {
                        if (it.isEmpty()) {
                            resources.getString(R.string.No_Respond)
                        } else {
                            it[0].text_answer!!
                        }
                    }

                    ExerciseTypeEn.VIEW_TODO_TASK.id -> {
                        if (it.isNotEmpty())
                            resources.getString(R.string.Checked)
                        else
                            resources.getString(R.string.Unchecked)
                    }

                    ExerciseTypeEn.VIEW_SCALE_QUESTION.id -> {
                        if (it.isEmpty())
                            resources.getString(R.string.No_Respond)
                        else
                            it[0].text_answer!! + " / " + itemView.context.resources.getInteger(R.integer.EJ_Entry_Max_Scale_Value)
                    }

                    ExerciseTypeEn.VIEW_CHOOSE_OPTION.id -> {
                        if (it.isEmpty()) {
                            resources.getString(R.string.No_Respond)
                        } else {
                            it[0].answer
                        }
                    }

                    ExerciseTypeEn.VIEW_MULTICHOOSE_OPTION.id -> {
                        if (it.isNotEmpty()) {
                            var answer = ""
                            for (elem in it) {
                                answer += elem.answer + "\n"
                            }
                            answer

                        } else {
                            resources.getString(R.string.None_Checked)
                        }
                    }
                    else -> {resources.getString(R.string.No_Respond)}
                }
        }
    }
}
