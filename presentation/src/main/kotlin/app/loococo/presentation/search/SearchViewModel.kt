package app.loococo.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.loococo.domain.model.network.Resource
import app.loococo.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: SearchUseCase) : ViewModel() {
    private val _searchWordFlow: MutableStateFlow<String> = MutableStateFlow("")
    val searchWordFlow: StateFlow<String> = _searchWordFlow

    fun updateSearchWord(searchWord: String) {
        _searchWordFlow.value = searchWord
        viewModelScope.launch {
            Log.e("-------------", "$searchWord")
            useCase.search(searchWord).collect {
                when (it) {
                    is Resource.Success -> {
                        Log.e("----------------", "${it.data}")
                    }

                    is Resource.Error -> {
                        Log.e("----------------", "${it.exception}")
                    }
                }
            }
        }
    }
}