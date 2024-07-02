package app.loococo.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.loococo.domain.model.Search
import app.loococo.domain.usecase.BookMarksUseCase
import app.loococo.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val bookMarksUseCase: BookMarksUseCase
) : ViewModel() {

    private val _keyWordFlow = MutableStateFlow("")
    val keyWordFlow: StateFlow<String> = _keyWordFlow

    val searchWordListFlow: Flow<PagingData<Search>> = _keyWordFlow
        .debounce(1000)
        .distinctUntilChanged()
        .flatMapLatest { searchUseCase.search(it) }
        .cachedIn(viewModelScope)

    fun updateSearchWord(searchWord: String) {
        _keyWordFlow.value = searchWord.trim()
    }

    fun updateBookmark(search: Search) {
        viewModelScope.launch {
            val action = if (search.state) bookMarksUseCase::delete else bookMarksUseCase::insert
            action(search)
        }
    }
}
