package nebraszka.selfaid.ui.adapters


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import nebraszka.selfaid.R
import nebraszka.selfaid.data.entities.Entry
import nebraszka.selfaid.databinding.RowEntryBinding
import nebraszka.selfaid.tools.DeleteEntryPickerHandler
import nebraszka.selfaid.tools.datetools.DateEditor
import nebraszka.selfaid.ui.viewmodels.EntryListViewModel


class EntryViewHolder(private val binding: RowEntryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createViewHolder(parent: ViewGroup): EntryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = RowEntryBinding.inflate(inflater, parent, false)
            return EntryViewHolder(binding)
        }
    }

    fun bind(entry: Entry, position: Int) {
        itemView.id = entry.id
        val reverseDate = DateEditor.reverseDate(entry.date)

        binding.btnEntry.text =
            if (entry.title.isNullOrBlank())
                reverseDate
            else
                entry.title + '\n' + reverseDate

        val color =
            if (position % 2 == 0 && position % 3 != 0) {
                R.color.said_peach
            } else if (position % 3 == 0) {
                R.color.said_pink_light
            } else {
                R.color.said_pink_dark
            }

        binding.btnEntry.setBackgroundColor(
            binding.btnEntry.context.resources.getColor(color)
        )

        binding.btnDeleteEntry.backgroundTintList = itemView.context.resources.getColorStateList(color)
    }

    fun setUpEntryClickListener() {
        binding.btnEntry.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("EXTRA_ENTRY_ID", itemView.id)
            itemView.findNavController().navigate(R.id.action_entryListFragment_to_savedEntryFragment, bundle)
        }
    }


    fun setUpDeleteClickListener(viewModel: EntryListViewModel){
        binding.btnDeleteEntry.setOnClickListener {
            DeleteEntryPickerHandler.deleteEntry(itemView.context, viewModel, itemView.id)
        }
    }
}