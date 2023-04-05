package edu.phystech.kirillgrachoff.mipt.ui.viewmodels.places

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ListUiState(
    val catalog: CatalogResponse
)

interface ListEvent {
    suspend fun prepare()
    val update: (ListUiState) -> ListUiState
}

class ListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ListUiState())
    val uiState = _uiState.asStateFlow()

    suspend fun confirm(event: ListEvent) {
        event.prepare()
        _uiState.update(event.update)
    }

    val handler: suspend (ListEvent) -> Unit = {
        confirm(it)
    }
}