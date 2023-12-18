package nebraszka.selfaid.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nebraszka.selfaid.data.repository.SelfAIDRepository
import javax.inject.Inject


/**
 * STATE:
 * - entries - date, title (everything without id)
 * - deleteEntryById
 */
@HiltViewModel
class EntryListViewModel @Inject constructor(private val repository: SelfAIDRepository) : ViewModel() {

    val allEntries = repository.allEntries.asLiveData()
    fun deleteByEntryId(entryId: Int) = viewModelScope.launch {
        repository.deleteByEntryId(entryId)
    }
}

fun deleteEmotionsAPI(){

}