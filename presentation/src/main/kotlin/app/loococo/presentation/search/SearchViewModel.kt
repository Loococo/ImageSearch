package app.loococo.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.loococo.domain.model.Search
import app.loococo.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: SearchUseCase) : ViewModel() {
    private val _keyWordFlow: MutableStateFlow<String> = MutableStateFlow("")

    val searchWordListFlow: Flow<PagingData<Search>> =
        _keyWordFlow
            .debounce(1000)
            .filter { it.isNotEmpty() }
            .distinctUntilChanged()
            .flatMapLatest { keyword ->
                useCase.search(keyword)
            }.cachedIn(viewModelScope)

    fun updateSearchWord(searchWord: String) {
        if (searchWord.isBlank()) return
        _keyWordFlow.value = searchWord
    }
}
