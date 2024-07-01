package app.loococo.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.loococo.presentation.theme.Black
import app.loococo.presentation.utils.ImageSearchIcons

@Composable
fun ImageSearchIconButton(
    size: Dp,
    icon: ImageVector,
    description: String,
    color: Color = Black,
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier.size(size),
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier.size(size),
            tint = color
        )
    }
}

@Composable
fun ImageSearchIconButton(
    modifier: Modifier,
    size: Dp,
    icon: ImageVector,
    description: String,
    color: Color = Black,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier.size(size),
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier.size(size),
            tint = color
        )
    }
}

@Composable
fun ImageSearchCheckBoxButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .size(23.dp)
            .clickable(
                onClick = {
                    onCheckedChange(!checked)
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = if (checked) ImageSearchIcons.Bookmarks else ImageSearchIcons.BookmarksBorder,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
    }
}