package nebraszka.selfaid.ui.adapters

import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nebraszka.selfaid.R
import nebraszka.selfaid.data.local.entities.RespondEntity
import nebraszka.selfaid.enums.ExerciseTypeEn
class RespondsHandler {
    companion object {
        fun getResponds(recyclerView: RecyclerView, pageId: Int): List<RespondEntity> {

            val responds: MutableList<RespondEntity> = mutableListOf()

            for (i in 0 until recyclerView.childCount) {
                val child: View = recyclerView.getChildAt(i)
                val viewHolder = recyclerView.getChildViewHolder(child)

                when (ExerciseTypeEn.findById(viewHolder.itemViewType)) {
                    ExerciseTypeEn.VIEW_QUESTION -> {
                        val txt = child.findViewById(R.id.etQuestionRow) as TextView
                        val answer = txt.text.toString()
                        if (answer.isEmpty())
                            continue
                        else
                            responds += RespondEntity(
                                child.id, pageId, null, answer
                            )
                    }

                    ExerciseTypeEn.VIEW_CHOOSE_OPTION -> {
                        val chosenAnswer = getChosenAnswer(child.findViewById(R.id.llAnswerSuggestions) as LinearLayout)
                        if (chosenAnswer == null){
                            continue
                        }
                        else
                            responds += RespondEntity(
                                child.id, pageId, chosenAnswer, null
                            )
                    }

                    ExerciseTypeEn.VIEW_MULTICHOOSE_OPTION -> {
                        val checkedBoxes = getCheckedBoxes(child.findViewById(R.id.llAnswerSuggestions) as LinearLayout)
                        if (checkedBoxes.isEmpty())
                            continue
                        else {
                            for (checkedBox in checkedBoxes) {
                                responds += RespondEntity(
                                    child.id, pageId, checkedBox, null
                                )
                            }
                        }
                    }

                    ExerciseTypeEn.VIEW_SCALE_QUESTION -> {
                        val answer = child.findViewById<ProgressBar>(R.id.scaleQuestionRow).progress
                        responds += RespondEntity(
                            child.id, pageId, null, answer.toString()
                        )
                    }

                    ExerciseTypeEn.VIEW_TODO_TASK -> {
                        val isDone = child.findViewById<CheckBox>(R.id.checkBoxTaskRow).isChecked
                        if (isDone) {
                            responds += RespondEntity(
                                child.id, pageId, null, isDone.toString()
                            )
                        }
                    }
                    else ->
                        continue
                }
            }
            return responds
        }

        private fun getChosenAnswer(ll: LinearLayout): Int? {
            val radioGroup: RadioGroup = ll.getChildAt(0) as RadioGroup
            val checkedId = radioGroup.checkedRadioButtonId
            return if (checkedId == -1)
                null
            else
                radioGroup.checkedRadioButtonId
        }

        private fun getCheckedBoxes(ll: LinearLayout): MutableList<Int> {
            val chosenAnswers: MutableList<Int> = mutableListOf()
            val childrenNo = ll.childCount
            for (i in 0 until childrenNo) {
                val checkBox = ll.getChildAt(i) as CheckBox
                if (checkBox.isChecked)
                    chosenAnswers += checkBox.id
            }
            return chosenAnswers
        }
    }
}