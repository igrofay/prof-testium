package five.head.proftestium.training.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import five.head.core.domain.model.training.DocumentInfoModel
import five.head.core.domain.model.training.TestInfoModel
import five.head.proftestium.R
import five.head.proftestium.common.ui.appbar.AppBar
import five.head.proftestium.common.ui.shadow.advancedShadow
import five.head.proftestium.common.vm.rememberVM
import five.head.proftestium.training.view_model.LecturesVM
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun LecturesScreen(
    startTest: (String) -> Unit
) {
    val lecturesVM by rememberVM<LecturesVM>()
    val state by lecturesVM.collectAsState()
    Column {
        AppBar(label = stringResource(R.string.lectures))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(vertical = 28.dp),
            verticalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    for (item in state.documents){
                        DocumentInfoItem(documentInfoModel = item)
                    }
                }
            }
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(R.string.tests).uppercase(),
                            style = MaterialTheme.typography.h4.copy(
                                MaterialTheme.colors.primary
                            ),
                        )
                        Divider(
                            startIndent = 18.dp,
                            thickness = 2.dp,
                            color = MaterialTheme.colors.primary
                        )
                    }
                    for (item in state.tests){
                        TestInfoItem(testInfoModel = item) {
                            startTest(item.id)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DocumentInfoItem(
    documentInfoModel: DocumentInfoModel
) {
    val context = LocalContext.current
    Row(
        modifier =
        Modifier
            .padding(start = 30.dp)
            .fillMaxWidth()
            .advancedShadow(
                alpha = 0.15f,
                shadowBlurRadius = 4.dp
            )
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(documentInfoModel.urlFile)
                }
                context.startActivity(intent)
            }
            .background(Color.White)
            .padding(vertical = 18.dp)
            .padding(start = 18.dp, end = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.document),
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)
        )
        Text(
            text = documentInfoModel.name,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                color = MaterialTheme.colors.primary,
            ),
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = painterResource(R.drawable.download),
            contentDescription = null,
            modifier = Modifier
                .size(27.dp)
        )
    }
}

@Composable
private fun TestInfoItem(
    testInfoModel: TestInfoModel,
    open: ()->Unit
) {
    Row(
        modifier = Modifier
            .padding(start = 30.dp)
            .fillMaxWidth()
            .advancedShadow(
                alpha = 0.15f,
                shadowBlurRadius = 4.dp
            )
            .clickable(onClick = open)
            .background(Color.White)
            .padding(vertical = 18.dp)
            .padding(start = 18.dp, end = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.info_circle),
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)
        )
        Text(
            text = testInfoModel.name,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                color = MaterialTheme.colors.primary,
            ),
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
    }
}