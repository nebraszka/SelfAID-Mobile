package nebraszka.selfaid.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nebraszka.selfaid.data.SelfAIDRepository
import nebraszka.selfaid.data.entities.Entry


/**
 * STATE:
 * - entries - date, title (everything without id)
 * - deleteEntryById
 */
class EntryListViewModel(private val repository: SelfAIDRepository) : ViewModel() {

    val allEntries = repository.allEntries.asLiveData()
    fun deleteByEntryId(entryId: Int) = viewModelScope.launch {
        repository.deleteByEntryId(entryId)
    }
}

class EntryListViewModelFactory(private val repository: SelfAIDRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EntryListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EntryListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}