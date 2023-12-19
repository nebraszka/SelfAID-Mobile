package nebraszka.selfaid.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import nebraszka.selfaid.R
import nebraszka.selfaid.SelfAIDApplication
import nebraszka.selfaid.data.local.entities.EntryEntity
import nebraszka.selfaid.data.local.entities.EntryPageEntity
import nebraszka.selfaid.data.local.entities.RespondEntity
import nebraszka.selfaid.databinding.FragmentNewEntryBinding
import nebraszka.selfaid.ui.tools.ViewVisibilityManager
import nebraszka.selfaid.ui.tools.datetools.DateEditor
import nebraszka.selfaid.ui.tools.datetools.DatePickerHandler
import nebraszka.selfaid.ui.adapters.EmotionSectionManager
import nebraszka.selfaid.ui.adapters.EmotionSelectedListener
import nebraszka.selfaid.ui.adapters.RespondsHandler
import nebraszka.selfaid.ui.adapters.exercises.ExerciseAdapter
import nebraszka.selfaid.ui.viewmodels.EntryViewModel

@AndroidEntryPoint
class NewEntryFragment : Fragment() {

    private var _binding: FragmentNewEntryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EntryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.entryId.observe(viewLifecycleOwner) { entryId ->
            if (entryId != null) {
                viewModel.addPage(
                    EntryPageEntity(
                        1,
                        viewModel.chosenEmotion?.emotion!!,
                        entryId.toInt()
                    )
                )
            }
        }

        viewModel.pageId.observe(viewLifecycleOwner) { pageId ->
            if (pageId != null) {
                viewModel.addResponds(
                    RespondsHandler.getResponds(
                        binding.rvTasks,
                        pageId.toInt()
                    ) as MutableList<RespondEntity>
                )
                findNavController().navigate(R.id.action_newEntryFragment_to_entryListFragment)
            }
        }

        manageRecordOfEntry()

        binding.btnDatePicker.setOnClickListener {
            DatePickerHandler.changeTextViewDate(binding.root.context, binding.tvChosenDate)
        }

        setUpEmotionSection()
        prepareListOfExercises()

        binding.btnSaveEntry.setOnClickListener {
            saveResponds()
        }

    }

    // TODO : Data saved in bundle is not restored after screen rotation
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString("SAVED_DATE", binding.tvChosenDate.text.toString())
//        outState.putString("SAVED_TITLE", binding.tvEJTitle.text.toString())
//    }


    private fun manageRecordOfEntry() {
        binding.tvChosenDate.doOnTextChanged { _, _, _, _ ->
            ViewVisibilityManager.manage(binding.btnSaveEntry, true)
        }
    }

    private fun prepareListOfExercises() {
        val adapter = ExerciseAdapter(this)
        viewModel.allExercises.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.rvTasks.layoutManager = LinearLayoutManager(this.context)
            binding.rvTasks.adapter = adapter
        }
    }

    private fun setUpEmotionSection() {
        binding.spnEmotions.onItemSelectedListener = EmotionSelectedListener(viewModel, this)

        viewModel.emotions.observe(viewLifecycleOwner) { emotions ->
            // Update the spinner with the new data
            context?.let { context ->
                EmotionSectionManager.initiateEmotionSpinner(context, emotions, binding.spnEmotions)
            }
        }

        binding.btnEmotionInfo.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("EXTRA_EMOTION_NAME", viewModel.chosenEmotion!!.emotion)
            bundle.putString("EXTRA_EMOTION_DESCRIPTION", viewModel.chosenEmotion!!.description)
            val dialog = EmotionDescriptionDialog()
            dialog.arguments = bundle
            dialog.show(parentFragmentManager, "EmotionDescriptionDialog")
        }

    }

    private fun saveResponds() { // TODO: przesunąć odpowiedzialność na inną klasę?
        val date = binding.tvChosenDate.text.toString()
        val title = binding.etEJTitle.text.toString()

        viewModel.addEntry(
            EntryEntity(
                DateEditor.reverseDate(date),
                title
            )
        )
    }
}