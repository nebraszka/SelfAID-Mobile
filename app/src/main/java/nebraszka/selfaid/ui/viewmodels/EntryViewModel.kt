package nebraszka.selfaid.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nebraszka.selfaid.data.SelfAIDRepository
import nebraszka.selfaid.data.entities.Emotion
import nebraszka.selfaid.data.entities.Entry
import nebraszka.selfaid.data.entities.EntryPage
import nebraszka.selfaid.data.entities.Exercise
import nebraszka.selfaid.data.entities.Respond

class EntryViewModel(private val repository: SelfAIDRepository) : ViewModel() {
    var chosenEmotion: Emotion? = null
    val allEmotions: LiveData<List<Emotion>> = repository.allEmotions.asLiveData()
    val allExercises: LiveData<List<Exercise>> = repository.allExercises.asLiveData()

    var entryId = MutableLiveData<Long>()
    var pageId = MutableLiveData<Long>()

    fun getEmotion(name: String): LiveData<Emotion> {
        return repository.getEmotion(name).asLiveData()
    }

    fun addEntry(entry: Entry) {
        viewModelScope.launch {
            entryId.postValue(repository.insertEntry(entry))
        }
    }

    fun addPage(page: EntryPage) {
        viewModelScope.launch {
            pageId.postValue(repository.insertEntryPage(page))
        }
    }

    fun addResponds(responds: List<Respond>) {
        viewModelScope.launch {
            repository.insertResponds(responds)
        }
    }

    class EntryViewModelFactory(private val repository: SelfAIDRepository) :
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