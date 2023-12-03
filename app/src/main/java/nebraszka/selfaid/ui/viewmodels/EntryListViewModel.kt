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
        val allEntries = repository.getAllEntries()
    //        val allEntries = repository.allEntries.asLiveData() // VERSION FOR DB
fun deleteByEntryId(entryId: Int) = viewModelScope.launch{
        repository.deleteByEntryId(entryId)
    }

//    private val _uiState = MutableStateFlow(EntryListState())
//    val uiState: StateFlow<EntryListState> = _uiState.asStateFlow()
//
//    private var fetchJob: Job? = null
//
//    fun fetchEntries() {
//        fetchJob?.cancel()
//        fetchJob = viewModelScope.launch {
//            val entries = repository.getAllEntries()
//            _uiState.value = uiState.value.copy(entries = entries)
//        }
//    }
}
//
//data class EntryListState(
//    val entries: List<Entry> = emptyList(),
//    val userMessages: List<String> = emptyList()
//)

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