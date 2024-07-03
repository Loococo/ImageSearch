package app.loococo.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import app.loococo.domain.model.error.SearchError
import app.loococo.presentation.R
import app.loococo.presentation.theme.Red10
import app.loococo.presentation.theme.White

@Composable
fun ImageSearchErrorPopup(
    errorState: SearchError,
    onRefresh: () -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismissRequest.invoke() }
    ) {
        Column(
            modifier = Modifier
                .background(White, RoundedCornerShape(10.dp))
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val errorMessage = when (errorState) {
                is SearchError.NoInternet -> stringResource(id = R.string.search_internet_error)
                is SearchError.ServerError -> stringResource(id = R.string.search_server_error)
                else -> stringResource(id = R.string.search_unknown_error)
            }
            ImageSearchBodyText(text = errorMessage)
            Button(
                onClick = {
                    onRefresh.invoke()
                    onDismissRequest.invoke()
                },
                colors = ButtonDefaults.buttonColors().copy(containerColor = Red10),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                ImageSearchLabelText(text = "새로고침")
            }
        }
    }
}

@Composable
fun rememberImageSearchErrorPopupState(
    initialValue: Boolean = false,
    errorState: SearchError = SearchError.None
): ShowPopupState {
    return remember { ShowPopupState(initialValue, errorState) }
}

@Stable
class ShowPopupState(initialValue: Boolean, errorState: SearchError) {
    var showPopupState by mutableStateOf(initialValue)
    var errorState by mutableStateOf(errorState)

    fun showPopup(errorState: SearchError) {
        showPopupState = true
        this.errorState = errorState
    }

    fun dismissPopup() {
        showPopupState = false
    }
}