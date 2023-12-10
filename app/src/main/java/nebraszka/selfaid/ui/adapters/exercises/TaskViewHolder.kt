package nebraszka.selfaid.ui.adapters.exercises

import android.view.View
import androidx.lifecycle.LifecycleOwner
import nebraszka.selfaid.data.entities.Exercise

class TaskViewHolder(
    view: View, owner: LifecycleOwner, exercise: Exercise
) : ExerciseViewHolderBinder(view, owner, exercise) {

    override fun bind() {
        bindTopicAndId()
    }
}