package five.head.proftestium.profle_user.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: ()->Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .alpha(if (enabled) 1f else 0.7f)
            .background(MaterialTheme.colors.primary)
            .clickable(enabled= enabled,onClick = onClick)
            .padding(vertical = 13.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = label,
            style = MaterialTheme.typography.subtitle1
                .copy(Color.White)
        )
    }
}