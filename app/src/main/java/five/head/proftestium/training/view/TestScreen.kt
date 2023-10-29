package five.head.proftestium.training.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import five.head.proftestium.R
import five.head.proftestium.common.ui.appbar.AppBar
import five.head.proftestium.common.ui.shadow.advancedShadow
import five.head.proftestium.common.vm.rememberVM
import five.head.proftestium.profle_user.view.CustomButton
import five.head.proftestium.training.model.TestEvent
import five.head.proftestium.training.model.TestState
import five.head.proftestium.training.view_model.TestScreenVM
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun TestScreen(
    exit: () -> Unit
) {
    val testScreenVM by rememberVM<TestScreenVM>()
    val state by testScreenVM.collectAsState()
    Column {
        AppBar(label = stringResource(R.string.testing))
        when (val tState =state) {
            TestState.Load -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
            is TestState.CurrentQuestion -> QuestionView(tState){
                testScreenVM.onEvent(TestEvent.Answer(it))
            }

            is TestState.EndOfTest -> ThanksForTakingTest(tState,exit)
        }
    }
}

@Composable
fun QuestionView(
    question: TestState.CurrentQuestion,
    answer: (Int)->Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(0.8f)
            .padding(25.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "${stringResource(R.string.points)}: ${question.points}",
                fontSize = 14.sp,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.W400,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Text(
                text = "${question.position}/${question.numberOfPositions}",
                fontSize = 14.sp,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.W400,
                modifier = Modifier.align(Alignment.TopEnd)
            )
            Text(
                text = question.name,
                fontSize = 16.sp,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
        ){
            itemsIndexed(question.answers){ index, item ->
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
                            answer(index)
                        }
                        .background(Color.White)
                        .padding(vertical = 18.dp)
                        .padding(start = 18.dp, end = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = item,
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
        }
    }
}

@Composable
fun ThanksForTakingTest(
    endOfTest: TestState.EndOfTest,
    exit: ()-> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ){
            Text(
                text = stringResource(R.string.thanks_for_taking_test),
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth(0.7f),
            )
            Text(
                text = "${stringResource(R.string.you_have_passed_test)}: ${endOfTest.name}",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth(0.7f),
            )
            Text(
                text = "${stringResource(R.string.your_result)}: ${endOfTest.points}",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.7f),
                color = MaterialTheme.colors.primary,
            )
        }
        CustomButton(
            label = stringResource(R.string.and_all_best_to_you),
            modifier = Modifier
                .padding(30.dp)
                .padding(WindowInsets.navigationBars.asPaddingValues())
                .align(Alignment.BottomCenter),
            onClick = exit
        )
    }
}