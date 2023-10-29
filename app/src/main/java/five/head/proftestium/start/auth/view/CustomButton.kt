package five.head.proftestium.start.auth.view

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import five.head.hakaton.common.ui.click.scaleClick

@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: ()->Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled, onClick = onClick)
            .background(Color.White)
            .padding(vertical = 20.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle2
                .copy(color = MaterialTheme.colors.primary),
        )
    }
}