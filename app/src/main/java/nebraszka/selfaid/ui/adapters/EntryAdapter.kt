package nebraszka.selfaid.ui.adapters

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import nebraszka.selfaid.data.local.entities.EntryEntity
import nebraszka.selfaid.ui.viewmodels.EntryListViewModel

class EntryAdapter(private val owner: LifecycleOwner, private val viewModel: EntryListViewModel) : ListAdapter<EntryEntity, EntryViewHolder>(
    EntryComparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        return EntryViewHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, position)
        holder.setUpEntryClickListener()
        holder.setUpDeleteClickListener(viewModel)
    }

    class EntryComparator : DiffUtil.ItemCallback<EntryEntity>() {
        override fun areItemsTheSame(oldItem: EntryEntity, newItem: EntryEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: EntryEntity, newItem: EntryEntity): Boolean {
            return oldItem.date == newItem.date
        }
    }
}