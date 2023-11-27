package nebraszka.selfaid.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nebraszka.selfaid.R
import nebraszka.selfaid.ui.viewmodels.NewEntryViewModel

class NewEntryFragment : Fragment() {

    companion object {
        fun newInstance() = NewEntryFragment()
    }

    private lateinit var viewModel: NewEntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_entry, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewEntryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}