package five.head.proftestium.test_analytics.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import five.head.proftestium.R
import five.head.proftestium.common.ui.appbar.AppBar
import five.head.proftestium.common.ui.shadow.advancedShadow
import five.head.proftestium.common.vm.rememberVM
import five.head.proftestium.test_analytics.view_model.TestAnalyticsVM
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun TestAnalyticsScreen() {
    val testAnalyticsVM by rememberVM<TestAnalyticsVM>()
    val state by testAnalyticsVM.collectAsState()
    Column {
        AppBar(label = stringResource(R.string.rating))
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            contentPadding = PaddingValues(
                vertical = 16.dp
            )
        ){
            item{
                Row(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                        .advancedShadow(
                            alpha = 0.25f,
                            shadowBlurRadius = 4.dp
                        )
                        .background(Color.White)
                        .padding(vertical = 12.dp, horizontal = 25.dp)
                ) {
                    Text(
                        text =state.currentDepartment?.name ?: stringResource(R.string.department),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            items(state.listAnalyticsOnUserTest){

            }
        }
    }
}