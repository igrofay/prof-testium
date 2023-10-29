package five.head.proftestium.test_analytics.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import five.head.proftestium.R
import five.head.proftestium.common.ui.appbar.AppBar
import five.head.proftestium.common.ui.shadow.advancedShadow
import five.head.proftestium.common.vm.rememberVM
import five.head.proftestium.test_analytics.model.TestAnalyticsEvent
import five.head.proftestium.test_analytics.view_model.TestAnalyticsVM
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun TestAnalyticsScreen() {
    val testAnalyticsVM by rememberVM<TestAnalyticsVM>()
    val state by testAnalyticsVM.collectAsState()
    Column {
        AppBar(label = stringResource(R.string.rating))
        if (state.listAnalyticsOnUserTest.isEmpty()){
            var expandedDropdownMenu by remember {
                mutableStateOf(false)
            }
            Box(
                modifier = Modifier
                    .padding(24.dp)
            ){
                Row(
                    modifier = Modifier
                        .clickable { expandedDropdownMenu = true }
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
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp),
                        tint = MaterialTheme.colors.primary
                    )
                }
                DropdownMenu(
                    expanded = expandedDropdownMenu,
                    onDismissRequest = { expandedDropdownMenu = false }
                ) {
                    for (item in state.listDepartment){
                        DropdownMenuItem(onClick = {
                            testAnalyticsVM.onEvent(TestAnalyticsEvent.DepartmentSelected(item))
                        }) {
                            Text(
                                text = item.name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W400,
                                color = MaterialTheme.colors.primary,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                contentPadding = PaddingValues(
                    vertical = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                items(state.listAnalyticsDepartment){ item->
                    InfoItem(
                        label= item.fullname,
                        description = "${item.countPoints} баллов",
                        colorDescription = Color(0xFF9B1919),
                    ){
                        testAnalyticsVM.onEvent(TestAnalyticsEvent.LoadAnalyticsOnUserTest(item.userId))
                    }
                }
            }
        }else{
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                contentPadding = PaddingValues(
                    vertical = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                items(state.listAnalyticsOnUserTest){ item->
                    InfoItem(
                        label= item.testName,
                        description = "${item.countPoints} из ${item.maxCountPoints}",
                        colorDescription = Color(0xFF9B1919),
                    )
                }
            }
            BackHandler {
                testAnalyticsVM.onEvent(TestAnalyticsEvent.BackToUsers)
            }
        }
    }
}

@Composable
fun InfoItem(
    label: String,
    description: String,
    colorDescription: Color,
    onClick: (()->Unit)? = null
) {
    Row(
        modifier =
        Modifier
            .padding(start = 30.dp)
            .fillMaxWidth()
            .advancedShadow(
                alpha = 0.15f,
                shadowBlurRadius = 4.dp
            )
            .background(Color.White)
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(vertical = 28.dp)
            .padding(start = 18.dp, end = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                color = MaterialTheme.colors.primary,
            ),
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = description,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                color = colorDescription,
            ),
        )
    }
}