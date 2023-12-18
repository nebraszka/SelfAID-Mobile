package pl.nebraszka.selfaid.adapters.exercises

import android.view.View
import androidx.lifecycle.LifecycleOwner
import nebraszka.selfaid.data.local.entities.ExerciseEntity
import nebraszka.selfaid.ui.adapters.exercises.ExerciseViewHolderBinder

class QuestionViewHolder(
    view: View, owner: LifecycleOwner, exercise: ExerciseEntity
) : ExerciseViewHolderBinder(view, owner, exercise) {

    override fun bind() {
        bindTopicAndId()
    }
}
