package nebraszka.selfaid.ui.adapters.exercises

import android.view.View
import androidx.lifecycle.LifecycleOwner
import nebraszka.selfaid.data.local.entities.ExerciseEntity

class TaskViewHolder(
    view: View, owner: LifecycleOwner, exercise: ExerciseEntity
) : ExerciseViewHolderBinder(view, owner, exercise) {

    override fun bind() {
        bindTopicAndId()
    }
}