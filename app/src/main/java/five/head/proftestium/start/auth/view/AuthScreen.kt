package five.head.proftestium.start.auth.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeAnimationTarget
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import five.head.proftestium.R
import five.head.proftestium.common.model.PhoneVisualTransformation
import five.head.proftestium.start.auth.model.AuthEvent
import five.head.proftestium.start.auth.model.AuthSideEffect
import five.head.proftestium.start.auth.view_model.AuthVM
import five.head.proftestium.common.vm.rememberVM
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AuthScreen(
    goToEmployee: () -> Unit,
    goToManager: () -> Unit,
    goToOther: ()->Unit,
) {
    val authVM by rememberVM<AuthVM>()
    val context = LocalContext.current
    authVM.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is AuthSideEffect.Message -> Toast
                .makeText(context, sideEffect.text, Toast.LENGTH_SHORT)
                .show()
            AuthSideEffect.UserIsEmployee -> goToEmployee()
            AuthSideEffect.UserIsManager -> goToManager()
            AuthSideEffect.UserIsOther -> goToOther()
        }
    }
    val state by authVM.collectAsState()
    BoxWithConstraints(
        modifier = Modifier
            .padding(
                WindowInsets
                    .imeAnimationTarget
                    .asPaddingValues()
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
                val paddingHorizontal = remember(this@BoxWithConstraints.maxWidth) {
                    this@BoxWithConstraints.maxWidth / 8.25f
                }
                Text(
                    text = stringResource(R.string.sign_in) + "!",
                    style = MaterialTheme.typography.h6
                        .copy(Color.White),
                    modifier = Modifier.padding(bottom = 14.dp)
                )
                CustomTextField(
                    value = state.phone,
                    onValueChange = {
                        authVM.onEvent(AuthEvent.InputPhone(it))
                    },
                    hint = stringResource(R.string.phone_number),
                    modifier = Modifier
                        .padding(horizontal = paddingHorizontal),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    visualTransformation = PhoneVisualTransformation(
                        "+7(000)000-00-00",
                        '0'
                    )
                )
                CustomTextField(
                    value = state.password,
                    onValueChange = {
                        authVM.onEvent(AuthEvent.InputPassword(it))
                    },
                    hint = stringResource(R.string.password),
                    modifier = Modifier
                        .padding(horizontal = paddingHorizontal),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                    ),
                    visualTransformation = PasswordVisualTransformation('*')
                )
                CustomButton(
                    text = stringResource(R.string.to_come_in),
                    modifier = Modifier
                        .padding(horizontal = paddingHorizontal),
                ) {
                    authVM.onEvent(AuthEvent.TryAuth)
                }
            }
        }
    }
}