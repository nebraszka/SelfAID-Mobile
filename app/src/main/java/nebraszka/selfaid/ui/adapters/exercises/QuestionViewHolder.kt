package pl.nebraszka.selfaid.adapters.exercises

import android.view.View
import androidx.lifecycle.LifecycleOwner
import nebraszka.selfaid.data.entities.Exercise
import nebraszka.selfaid.ui.adapters.exercises.ExerciseViewHolderBinder

class QuestionViewHolder(
    view: View, owner: LifecycleOwner, exercise: Exercise
) : ExerciseViewHolderBinder(view, owner, exercise) {

    override fun bind() {
        bindTopicAndId()
    }
}
