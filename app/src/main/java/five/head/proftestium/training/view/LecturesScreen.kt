package five.head.proftestium.training.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import five.head.core.domain.model.training.DocumentInfoModel
import five.head.proftestium.R
import five.head.proftestium.common.ui.appbar.AppBar
import five.head.proftestium.common.vm.rememberVM
import five.head.proftestium.training.view_model.LecturesVM

@Composable
fun LecturesScreen() {
    val lecturesVM by rememberVM<LecturesVM>()
    Column {
        AppBar(label = stringResource(R.string.lectures))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ){

        }
    }
}

@Composable
private fun DocumentInfoItem(
    documentInfoModel: DocumentInfoModel
) {
    Row(
        modifier =
            Modifier
                .padding(start = 30.dp)
                .fillMaxWidth()
                .shadow(elevation = 4.dp, spotColor = Color(0x26000000), ambientColor = Color(0x26000000))
                .background(Color.White)
                .padding(18.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
    ){

    }
}