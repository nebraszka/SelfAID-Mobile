package nebraszka.selfaid.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nebraszka.selfaid.data.local.SelfAIDRepository


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

fun deleteEmotionsAPI(){

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