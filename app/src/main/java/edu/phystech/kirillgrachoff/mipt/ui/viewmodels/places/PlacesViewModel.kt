package edu.phystech.kirillgrachoff.mipt.ui.viewmodels.places

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ListUiState(
    val nearest: List<Place> = emptyList(),
    val popular: List<Place> = emptyList(),
    val commercial: Commercial? = null,
)

//interface ListEvent {
//    suspend fun prepare()
//    val update: (ListUiState) -> ListUiState
//}

@HiltViewModel
class PlacesViewModel @Inject constructor(val placesClient: Client) : ViewModel() {
    private val _uiState = MutableStateFlow(ListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            launch(Dispatchers.IO) {
                val catalog = placesClient.get()
                with (catalog) {
                    _uiState.emit(ListUiState(nearest, popular, commercial))
                }
            }
        }
    }
}