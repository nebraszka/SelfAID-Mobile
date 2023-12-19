package nebraszka.selfaid.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nebraszka.selfaid.data.local.entities.EmotionEntity
import nebraszka.selfaid.data.local.entities.EntryEntity
import nebraszka.selfaid.data.local.entities.ExerciseEntity
import nebraszka.selfaid.data.repository.SelfAIDRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class SavedEntryViewModel @Inject constructor(private val repository: SelfAIDRepositoryImpl) : ViewModel() {
    val allEJExercises: LiveData<List<ExerciseEntity>> = repository.allExercises.asLiveData()
    var entryId = MutableLiveData<Long>()

    fun getEntryInfo(entryId: Int): LiveData<EntryEntity> {
        return repository.getEntryInfo(entryId).asLiveData()
    }

    fun getEntryEmotion(entryId: Int): LiveData<EmotionEntity> {
        return repository.getEntryEmotion(entryId).asLiveData()
    }
}
