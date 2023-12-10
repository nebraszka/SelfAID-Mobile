package nebraszka.selfaid.ui.adapters.exercises

import android.view.View
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import nebraszka.selfaid.data.entities.Exercise
import nebraszka.selfaid.enums.ExerciseTypeEn

abstract class ExerciseViewHolderBinder(
    val view: View,
    val owner: LifecycleOwner,
    val exercise: Exercise
) {

    fun bindTopicAndId() {
        val exerciseType: ExerciseTypeEn = ExerciseTypeEn.findById(exercise.exerciseType)!!
        val tvTopic = view.findViewById<TextView>(exerciseType.topicTextView)
        tvTopic.text = exercise.topic
        view.id = exercise.id
    }

    abstract fun bind()
}