package app.loococo.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.loococo.domain.model.Search
import app.loococo.domain.usecase.BookMarksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val useCase: BookMarksUseCase
) : ViewModel() {
    private val _keyWordFlow: MutableStateFlow<String> = MutableStateFlow("")

    val searchWordListFlow: StateFlow<List<Search>> = _keyWordFlow
        .debounce(1000)
        .distinctUntilChanged()
        .flatMapLatest { useCase.getBookmarksByKeyword(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun updateSearchWord(searchWord: String) {
        _keyWordFlow.value = searchWord.trim()
    }

    fun deleteBookmark(search: Search) {
        viewModelScope.launch {
            useCase.delete(search.id)
        }
    }
}