package nebraszka.selfaid.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import nebraszka.selfaid.data.local.entities.EmotionEntity
import nebraszka.selfaid.data.local.entities.EntryEntity
import nebraszka.selfaid.data.local.entities.ExerciseEntity
import nebraszka.selfaid.data.repository.SelfAIDRepository

class SavedEntryViewModel(private val repository: SelfAIDRepository) : ViewModel() {
    val allEJExercises: LiveData<List<ExerciseEntity>> = repository.allExercises.asLiveData()
    var entryId = MutableLiveData<Long>()

    fun getEntryInfo(entryId: Int): LiveData<EntryEntity> {
        return repository.getEntryInfo(entryId).asLiveData()
    }

    fun getEntryEmotion(entryId: Int): LiveData<EmotionEntity> {
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