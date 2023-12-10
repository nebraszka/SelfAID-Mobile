package nebraszka.selfaid.ui.adapters

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LifecycleOwner
import nebraszka.selfaid.ui.viewmodels.EntryViewModel

class EmotionSelectedListener(
    private val viewModel: EntryViewModel,
    private val owner: LifecycleOwner
    ) : AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
            viewModel.getEmotion(parent!!.getItemAtPosition(pos).toString())
                .observe(owner) {
                    viewModel.chosenEmotion = it
                }
        }
        override fun onNothingSelected(p0: AdapterView<*>?) {
        }
}