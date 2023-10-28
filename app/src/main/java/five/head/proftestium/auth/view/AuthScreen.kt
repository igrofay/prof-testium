package five.head.proftestium.auth.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeAnimationSource
import androidx.compose.foundation.layout.imeAnimationTarget
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import five.head.proftestium.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AuthScreen() {
    BoxWithConstraints(
        modifier = Modifier
            .padding(
                bottom = WindowInsets
                    .imeAnimationTarget
                    .asPaddingValues()
                    .calculateBottomPadding()
            )
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.splash_back),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = this@BoxWithConstraints.maxWidth / 5f),
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.BottomCenter,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.75f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(13.dp, Alignment.CenterVertically)
            ) {
                val paddingHorizontal = remember(this@BoxWithConstraints.maxWidth){
                    this@BoxWithConstraints.maxWidth / 8.25f
                }
                Text(
                    text = stringResource(R.string.sign_in)+"!",
                    style = MaterialTheme.typography.h6
                        .copy(Color.White),
                    modifier = Modifier.padding(bottom = 14.dp)
                )
                CustomTextField(
                    value = "",
                    onValueChange = {} ,
                    hint = stringResource(R.string.phone_number),
                    modifier = Modifier
                        .padding(horizontal = paddingHorizontal)
                )
                CustomTextField(
                    value = "",
                    onValueChange = {} ,
                    hint = stringResource(R.string.password),
                    modifier = Modifier
                        .padding(horizontal =paddingHorizontal)
                )
                CustomButton(
                    text = stringResource(R.string.to_come_in),
                    modifier = Modifier
                        .padding(horizontal = paddingHorizontal),
                ){

                }
            }
        }
    }
}