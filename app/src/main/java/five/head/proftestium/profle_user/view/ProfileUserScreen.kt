package five.head.proftestium.profle_user.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import five.head.proftestium.R
import five.head.proftestium.common.model.PhoneVisualTransformation
import five.head.proftestium.common.ui.appbar.AppBar
import five.head.proftestium.common.vm.rememberVM
import five.head.proftestium.profle_user.model.ProfileUserEvent
import five.head.proftestium.profle_user.view_model.ProfileUserVM
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectState

@Composable
fun ProfileUserScreen() {
    val profileUserVM by rememberVM<ProfileUserVM>()
    val state by profileUserVM.collectAsState()
    Column {
        AppBar(label = stringResource(R.string.profile))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 35.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            CustomButton(
                label = stringResource(R.string.change_password),
            ){
                
            }
            Spacer(modifier = Modifier.height(10.dp))
            CustomButton(
                label = stringResource(R.string.notifications),
            ){

            }
            Spacer(modifier = Modifier.height(30.dp))
            CustomTextField(
                text = state.fullName,
                onValueChange = {profileUserVM.onEvent(ProfileUserEvent.InputFullName(it))},
                label = stringResource(R.string.fio).uppercase(),
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                text = state.phone,
                onValueChange = {},
                label = stringResource(R.string.phone_number).uppercase(),
                visualTransformation = PhoneVisualTransformation(
                    "+7(000)000-00-00",
                    '0'
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                readOnly = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                text = state.email,
                onValueChange = {profileUserVM.onEvent(ProfileUserEvent.InputEmail(it))},
                label = stringResource(R.string.email).uppercase(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement
                    .spacedBy(5.dp)
            ) {
                Text(
                    text = stringResource(R.string.language).uppercase(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = Color(0xFF000000)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color(0xFF000000))
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center,
                ){
                    Text(
                        text = "Русский",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W400,
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            CustomButton(
                label = stringResource(R.string.save),
                modifier = Modifier.fillMaxWidth(0.6f),
                enabled = state.isEdit
            ) {
               profileUserVM.onEvent(ProfileUserEvent.Save)
            }
        }
    }
}