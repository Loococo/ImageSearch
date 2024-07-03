package app.loococo.presentation.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.loococo.presentation.theme.Red
import app.loococo.presentation.theme.Red10
import app.loococo.presentation.theme.White

@ExperimentalMaterial3Api
@Composable
fun ImageSearchTopBar(title: String) {
    TopAppBar(
        title = {
            ImageSearchTitleText(text = title, fontWeight = FontWeight.Bold)
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = White
        )
    )
}

@Composable
fun ImageSearchBottomBar(content: @Composable RowScope.() -> Unit) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)),
        containerColor = White,
        tonalElevation = 1.dp,
        content = content
    )
}

@Composable
fun RowScope.ImageSearchNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Red,
            unselectedIconColor = Red,
            selectedTextColor = Red,
            unselectedTextColor = Red,
            indicatorColor = Red10
        ),
    )
}