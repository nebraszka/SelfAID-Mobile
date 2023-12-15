package nebraszka.selfaid.ui.fragments
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import nebraszka.selfaid.databinding.DialogEmotionDescriptionBinding

class EmotionDescriptionDialog : DialogFragment() {

    private var _binding: DialogEmotionDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogEmotionDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvEmotionName.text = arguments?.getString("EXTRA_EMOTION_NAME")
        binding.tvEmotionDescription.text = arguments?.getString("EXTRA_EMOTION_DESCRIPTION")

        binding.btnEmotionDescriptionBack.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(false)
        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
