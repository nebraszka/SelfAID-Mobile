package nebraszka.selfaid.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import nebraszka.selfaid.R
import nebraszka.selfaid.SelfAIDApplication
import nebraszka.selfaid.data.entities.Emotion
import nebraszka.selfaid.databinding.FragmentSavedEntryBinding
import nebraszka.selfaid.tools.ViewVisibilityManager
import nebraszka.selfaid.ui.adapters.AnswersAdapter
import nebraszka.selfaid.ui.adapters.EmotionSectionManager
import nebraszka.selfaid.ui.viewmodels.SavedEntryViewModel
import nebraszka.selfaid.ui.viewmodels.SavedEntryViewModelFactory

class SavedEntryFragment : Fragment() {

    private var _binding: FragmentSavedEntryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedEntryViewModel by viewModels {
        SavedEntryViewModelFactory((activity?.application as SelfAIDApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val entryId = arguments?.getInt("EXTRA_ENTRY_ID") ?: 0 // Use a default value if null

        manageButtonsVisibility()
        attachEntryInfo(entryId)

        viewModel.getEntryEmotion(entryId).observe(this.viewLifecycleOwner){
            if (it == null) {
                return@observe
            }
            bindEmotionInfo(it, entryId)
        }
    }

    private fun manageButtonsVisibility() {
        ViewVisibilityManager.manage(
            listOf(
                binding.btnDatePicker,
                binding.etEJTitle,
                binding.spnEmotions,
                binding.btnSaveEntry,
                binding.btnAddEmotion
            ), false
        )

        ViewVisibilityManager.manage(
            listOf(
                binding.tvEJTitle,
                binding.tvChooseEmotion,
                binding.tvChosenEmotion
            ), true
        )
    }

    private fun attachEntryInfo(entryId: Int) {
        viewModel.getEntryInfo(entryId).observe(this.viewLifecycleOwner, Observer {
            binding.tvChosenDate.text = it.date
            binding.tvEJTitle.text = it.title
        })
        prepareListOfResponds(entryId)
    }

    private fun prepareListOfResponds(entryId: Int) {
        val adapter = AnswersAdapter(this, entryId)
        viewModel.allEJExercises.observe(this.viewLifecycleOwner) {
            adapter.submitList(it)
            binding.rvTasks.layoutManager = LinearLayoutManager(binding.root.context)
            binding.rvTasks.adapter = adapter
        }
    }

    private fun bindEmotionInfo(emotion: Emotion, entryId: Int) {
        binding.tvChooseEmotion.text =
            this.resources.getString(R.string.EJ_SavedEntry_ChosenEmotion)
        binding.tvChosenEmotion.text = emotion.emotion

        binding.btnEmotionInfo.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("EXTRA_EMOTION_NAME", emotion.emotion)
            bundle.putString("EXTRA_EMOTION_DESCRIPTION", emotion.description)
            val dialog = EmotionDescriptionDialog()
            dialog.arguments = bundle
            dialog.show(parentFragmentManager, "EmotionDescriptionDialog")
        }
    }
}