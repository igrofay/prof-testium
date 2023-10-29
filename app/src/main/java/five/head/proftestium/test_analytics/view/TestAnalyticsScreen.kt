package five.head.proftestium.test_analytics.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import five.head.proftestium.R
import five.head.proftestium.common.ui.appbar.AppBar

@Composable
fun TestAnalyticsScreen() {
    Column {
        AppBar(label = stringResource(R.string.rating))

    }
}