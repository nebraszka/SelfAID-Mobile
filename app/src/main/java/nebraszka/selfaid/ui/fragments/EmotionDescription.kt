package nebraszka.selfaid.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import nebraszka.selfaid.R
import nebraszka.selfaid.databinding.FragmentEmotionDescriptionBinding
class EmotionDescription : Fragment() {

    private var _binding: FragmentEmotionDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmotionDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvEmotionName.text = arguments?.getString("EXTRA_EMOTION_NAME")
        binding.tvEmotionDescription.text = arguments?.getString("EXTRA_EMOTION_DESCRIPTION")

        binding.btnEmotionDescribtionBack.setOnClickListener {
            findNavController().navigate(R.id.action_emotionDescription_to_newEntryFragment)
        }
    }
}