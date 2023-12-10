package nebraszka.selfaid.tools.datetools

import android.app.Dialog
import android.content.Context
import android.widget.DatePicker
import android.widget.TextView
import nebraszka.selfaid.R
import java.util.Calendar
import java.util.Locale

class DatePickerHandler {

    companion object {
        private var datePickerDialog: Int = R.layout.dialog_date_picker

        private var date: Calendar = Calendar.getInstance()
        private var chosenYear = date[Calendar.YEAR]
        private var chosenMonth = date[Calendar.MONTH]
        private var chosenDay = date[Calendar.DAY_OF_MONTH]

        fun changeTextViewDate(context: Context, tv: TextView) {
            Locale.setDefault(Locale.forLanguageTag("pl"))

            val datePickerDialog = Dialog(context)
            datePickerDialog.setContentView(R.layout.dialog_date_picker)
            datePickerDialog.setCancelable(false)

            val datePicker = datePickerDialog.findViewById<DatePicker>(R.id.DatePicker)
            datePicker.init(chosenYear, chosenMonth, chosenDay) { _, year, month, dayOfMonth ->
                chosenYear = year
                chosenMonth = month
                chosenDay = dayOfMonth
            }

            val tvSave = datePickerDialog.findViewById<TextView>(R.id.tvDatePickerSave) // Replace with the correct ID
            tvSave.setOnClickListener {
                tv.text = context.getString(
                    R.string.Date,
                    DateEditor.addZeroIfNeeded(chosenDay),
                    DateEditor.addZeroIfNeeded(chosenMonth + 1),
                    chosenYear
                )
                datePickerDialog.dismiss()
            }

            val tvCancel = datePickerDialog.findViewById<TextView>(R.id.tvDatePickerCancel) // Replace with the correct ID
            tvCancel.setOnClickListener {
                datePickerDialog.dismiss()
            }

            datePickerDialog.show()
        }
    }
}
