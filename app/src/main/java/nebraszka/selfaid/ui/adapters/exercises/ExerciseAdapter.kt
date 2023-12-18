package nebraszka.selfaid.ui.adapters.exercises

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import nebraszka.selfaid.data.local.entities.ExerciseEntity

class ExerciseAdapter(private val owner: LifecycleOwner) : ListAdapter<ExerciseEntity, ExerciseViewHolder>(
    ExerciseComparator()
) {

    override fun getItemViewType(position: Int): Int {
        val myModel = getItem(position)
        return myModel.exerciseType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder.createViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, owner)
    }


    class ExerciseComparator : DiffUtil.ItemCallback<ExerciseEntity>() {
        override fun areItemsTheSame(oldItem: ExerciseEntity, newItem: ExerciseEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ExerciseEntity, newItem: ExerciseEntity): Boolean {
            return oldItem.topic == newItem.topic
        }
    }
}

