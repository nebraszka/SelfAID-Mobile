package nebraszka.selfaid.ui.adapters.exercises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import nebraszka.selfaid.enums.ExerciseTypeEn
import nebraszka.selfaid.data.local.entities.ExerciseEntity
import nebraszka.selfaid.exceptions.UnknownExerciseTypeException
import pl.nebraszka.selfaid.adapters.exercises.QuestionViewHolder

class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun createViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
            lateinit var view: View

            val layoutId: Int? = ExerciseTypeEn.findById(viewType)?.layoutId
            try {
                view = LayoutInflater.from(parent.context)
                    .inflate(layoutId!!, parent, false)

            } catch (e: Exception) {
                throw UnknownExerciseTypeException()
            }
            return ExerciseViewHolder(view)
        }
    }

    fun bind(exercise: ExerciseEntity, owner: LifecycleOwner) {
        when (exercise.exerciseType) {
            ExerciseTypeEn.VIEW_TODO_TASK.id ->
                TaskViewHolder(itemView, owner, exercise).bind()
            ExerciseTypeEn.VIEW_QUESTION.id ->
                QuestionViewHolder(itemView, owner, exercise).bind()
            ExerciseTypeEn.VIEW_SCALE_QUESTION.id ->
                ScaleQuestionViewHolder(itemView, owner, exercise).bind()
            ExerciseTypeEn.VIEW_CHOOSE_OPTION.id ->
                ChooseAnswerViewHolder(itemView, owner, exercise, false).bind()
            ExerciseTypeEn.VIEW_MULTICHOOSE_OPTION.id ->
                ChooseAnswerViewHolder(itemView, owner, exercise, true).bind()
            else ->
                throw Exception("todo: twoj wyjatek")
            // TODO : wyjątek
        }
    }
}

