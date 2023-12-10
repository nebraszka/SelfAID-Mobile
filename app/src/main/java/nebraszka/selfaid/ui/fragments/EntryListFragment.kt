package nebraszka.selfaid.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import nebraszka.selfaid.R
import nebraszka.selfaid.SelfAIDApplication
import nebraszka.selfaid.databinding.FragmentEntryListBinding
import nebraszka.selfaid.tools.ViewVisibilityManager
import nebraszka.selfaid.ui.adapters.EntryAdapter
import nebraszka.selfaid.ui.viewmodels.EntryListViewModel
import nebraszka.selfaid.ui.viewmodels.EntryListViewModelFactory

class EntryListFragment : Fragment() {

    private var _binding: FragmentEntryListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EntryListViewModel by viewModels {
        EntryListViewModelFactory((activity?.application as SelfAIDApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFirstEntry.setOnClickListener {
            findNavController().navigate(R.id.action_entryListFragment_to_newEntryFragment)
        }

        prepareListOfEntries()
    }

    private fun prepareListOfEntries() {
        val adapter = EntryAdapter(this, viewModel)

        viewModel.allEntries.observe(this.viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                adapter.submitList(it)
                binding.rvEntriesList!!.layoutManager = LinearLayoutManager(this.context)
                binding.rvEntriesList!!.adapter = adapter
                showMenuWithEntries()
            } else {
                showMenuWithoutEntries()
            }
        })
    }

    private fun showMenuWithEntries() {
        ViewVisibilityManager.manage(binding.btnFirstEntry, false)
        ViewVisibilityManager.manage(binding.btnNewEntry, true)
        ViewVisibilityManager.manage(binding.rvEntriesList, true)

        binding.btnNewEntry.setOnClickListener {
            findNavController().navigate(R.id.action_entryListFragment_to_newEntryFragment)
        }
    }

    private fun showMenuWithoutEntries() {
        ViewVisibilityManager.manage(binding.btnFirstEntry, true)
        ViewVisibilityManager.manage(binding.btnNewEntry, false)
        ViewVisibilityManager.manage(binding.rvEntriesList, false)
    }
}