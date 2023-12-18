package nebraszka.selfaid.ui.tools

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import nebraszka.selfaid.databinding.DialogDeleteEntryBinding
import nebraszka.selfaid.ui.viewmodels.EntryListViewModel

class DeleteEntryPickerHandler {

    companion object {

        fun deleteEntry(context: Context, viewModel: EntryListViewModel, entryId: Int) {
            val deletePickerDialog = Dialog(context)
            val dialogBinding = DialogDeleteEntryBinding.inflate(LayoutInflater.from(context))
            deletePickerDialog.setContentView(dialogBinding.root)
            deletePickerDialog.setCancelable(false)

            dialogBinding.tvDeleteCancel.setOnClickListener {
                deletePickerDialog.dismiss()
            }
            dialogBinding.tvDeleteConfirm.setOnClickListener {
                viewModel.deleteByEntryId(entryId)
                deletePickerDialog.dismiss()
            }

            deletePickerDialog.show()
        }
    }
}
