package nebraszka.selfaid.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nebraszka.selfaid.data.domain.model.Emotion
import nebraszka.selfaid.data.local.SelfAIDLocalRepository
import nebraszka.selfaid.data.local.entities.EntryEntity
import nebraszka.selfaid.data.local.entities.EntryPageEntity
import nebraszka.selfaid.data.local.entities.ExerciseEntity
import nebraszka.selfaid.data.local.entities.RespondEntity

class EntryViewModel(private val repository: SelfAIDLocalRepository) : ViewModel() {
    var chosenEmotion: Emotion? = null
    val allEmotions: LiveData<List<Emotion>> = repository.allEmotions.asLiveData()
    val allExercises: LiveData<List<ExerciseEntity>> = repository.allExercises.asLiveData()

    var entryId = MutableLiveData<Long>()
    var pageId = MutableLiveData<Long>()

    fun getEmotion(name: String): LiveData<Emotion> {
        return repository.getEmotion(name).asLiveData()
    }

    fun addEntry(entry: EntryEntity) {
        viewModelScope.launch {
            entryId.postValue(repository.insertEntry(entry))
        }
    }

    fun addPage(page: EntryPageEntity) {
        viewModelScope.launch {
            pageId.postValue(repository.insertEntryPage(page))
        }
    }

    fun addResponds(responds: List<RespondEntity>) {
        viewModelScope.launch {
            repository.insertResponds(responds)
        }
    }

    class EntryViewModelFactory(private val repository: SelfAIDLocalRepository) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EntryViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EntryViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}