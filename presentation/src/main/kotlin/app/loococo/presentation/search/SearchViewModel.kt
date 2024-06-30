package app.loococo.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import app.loococo.domain.model.Search
import app.loococo.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: SearchUseCase) : ViewModel() {
    private val _searchWordFlow: MutableStateFlow<String> = MutableStateFlow("")

    val searchWordListFlow: StateFlow<PagingData<Search>> =
        _searchWordFlow
            .flatMapLatest { searchWord ->
                useCase.search(searchWord)
                    .onStart { emit(PagingData.empty()) }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PagingData.empty()
            )

    fun updateSearchWord(searchWord: String) {
        _searchWordFlow.value = searchWord
    }
}