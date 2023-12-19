package nebraszka.selfaid.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nebraszka.selfaid.data.domain.model.Emotion
import nebraszka.selfaid.data.local.entities.EntryEntity
import nebraszka.selfaid.data.local.entities.EntryPageEntity
import nebraszka.selfaid.data.local.entities.ExerciseEntity
import nebraszka.selfaid.data.local.entities.RespondEntity
import nebraszka.selfaid.data.repository.SelfAIDRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(private val repository: SelfAIDRepositoryImpl) : ViewModel() {
    var chosenEmotion: Emotion? = null
//    val allEmotions: LiveData<List<Emotion>> = repository.getAllEmotions().asLiveData()
    val allExercises: LiveData<List<ExerciseEntity>> = repository.allExercises.asLiveData()

    var entryId = MutableLiveData<Long>()
    var pageId = MutableLiveData<Long>()

    private val _emotions = MutableLiveData<List<Emotion>>()
    val emotions: LiveData<List<Emotion>> = _emotions

    init {
        getAllEmotions()
    }

    fun getAllEmotions() {
        viewModelScope.launch {
            repository.getAllEmotions().collect { emotions ->
                _emotions.postValue(emotions)
            }
        }
    }

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

//    class EntryViewModelFactory() :
//        ViewModelProvider.Factory {
//
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(EntryViewModel::class.java)) {
//                @Suppress("UNCHECKED_CAST")
//                return EntryViewModel as T
//            }
//            throw IllegalArgumentException("Unknown ViewModel class")
//        }
//    }
}