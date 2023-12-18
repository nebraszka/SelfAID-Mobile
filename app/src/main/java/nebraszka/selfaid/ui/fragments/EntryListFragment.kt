package nebraszka.selfaid.ui.fragments

import android.os.Bundle
import android.util.Log
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
import nebraszka.selfaid.data.network.EmotionApiService
import nebraszka.selfaid.databinding.FragmentEntryListBinding
import nebraszka.selfaid.ui.tools.ViewVisibilityManager
import nebraszka.selfaid.ui.adapters.EntryAdapter
import nebraszka.selfaid.ui.viewmodels.EntryListViewModel
import nebraszka.selfaid.ui.viewmodels.EntryListViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

//            val call = (activity?.application as SelfAIDApplication).retrofitService.deleteEmotion()
//            call?.enqueue(object : Callback<String?> {
//                override fun onResponse(call: Call<String?>, response: Response<String?>) {
//                    if (response.isSuccessful) {
//                        // Handle successful response
//                        Log.d("DELETE", "Success: ${response.body()}")
//                    } else {
//                        // Handle failure response
//                        Log.d("DELETE", "Failed: ${response.code()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<String?>, t: Throwable) {
//                    // Handle network error or exception
//                    Log.e("DELETE", "Error: ${t.message}")
//                }
//            })

        }
    }

    private fun showMenuWithoutEntries() {
        ViewVisibilityManager.manage(binding.btnFirstEntry, true)
        ViewVisibilityManager.manage(binding.btnNewEntry, false)
        ViewVisibilityManager.manage(binding.rvEntriesList, false)
    }
}