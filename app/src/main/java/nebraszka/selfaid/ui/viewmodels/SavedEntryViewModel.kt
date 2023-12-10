package nebraszka.selfaid.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import nebraszka.selfaid.data.SelfAIDRepository
import nebraszka.selfaid.data.entities.Emotion
import nebraszka.selfaid.data.entities.Entry
import nebraszka.selfaid.data.entities.Exercise

class SavedEntryViewModel(private val repository: SelfAIDRepository) : ViewModel() {
    val allEJExercises: LiveData<List<Exercise>> = repository.allExercises.asLiveData()
    var entryId = MutableLiveData<Long>()

    fun getEntryInfo(entryId: Int): LiveData<Entry> {
        return repository.getEntryInfo(entryId).asLiveData()
    }

    fun getEntryEmotion(entryId: Int): LiveData<Emotion> {
        return repository.getEntryEmotion(entryId).asLiveData()
    }
}

class SavedEntryViewModelFactory(private val repository: SelfAIDRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedEntryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SavedEntryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}