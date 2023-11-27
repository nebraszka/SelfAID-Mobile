package nebraszka.selfaid.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import nebraszka.selfaid.R
import nebraszka.selfaid.databinding.FragmentEntryListBinding
import nebraszka.selfaid.ui.viewmodels.EntryListViewModel

class EntryListFragment : Fragment() {

    companion object {
        fun newInstance() = EntryListFragment()
    }

    private var _binding: FragmentEntryListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: EntryListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEJFirstEntry.setOnClickListener {
            findNavController().navigate(R.id.action_entryListFragment_to_newEntryFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EntryListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}