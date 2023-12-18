package nebraszka.selfaid.ui.adapters

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import nebraszka.selfaid.data.local.entities.ExerciseEntity
import nebraszka.selfaid.ui.adapters.exercises.ExerciseAdapter

class AnswersAdapter(val owner: LifecycleOwner, private val entryId: Int) :
    ListAdapter<ExerciseEntity, AnswersViewHolder>(
        ExerciseAdapter.ExerciseComparator()
    ) {

    override fun getItemViewType(position: Int): Int {
        val myModel = getItem(position)
        return myModel.exerciseType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswersViewHolder {
        return AnswersViewHolder.createViewHolder(parent, viewType, entryId)
    }

    override fun onBindViewHolder(holder: AnswersViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, owner)
    }
}
