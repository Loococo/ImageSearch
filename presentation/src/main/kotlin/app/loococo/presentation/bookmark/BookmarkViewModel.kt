package app.loococo.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.loococo.domain.model.Search
import app.loococo.domain.usecase.BookMarksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val useCase: BookMarksUseCase
) : ViewModel() {
    private val _keyWordFlow: MutableStateFlow<String> = MutableStateFlow("")

    val bookmarkListFlow: Flow<List<Search>> =
        _keyWordFlow
            .debounce(1000)
            .filter { it.isNotEmpty() }
            .distinctUntilChanged()
            .flatMapLatest { keyword ->
                useCase.getBookmarks()
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )
}