package five.head.proftestium.start.splash.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import five.head.proftestium.R
import five.head.proftestium.common.vm.rememberVM
import five.head.proftestium.start.splash.model.SplashSideEffect
import five.head.proftestium.start.splash.view_model.SplashVM
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SplashScreen(
    goToAuth: ()-> Unit,
    goToEmployee: () -> Unit,
    goToManager: () -> Unit,
    goToOther: ()->Unit,
) {
    val splashVM by rememberVM<SplashVM>()
    splashVM.collectSideEffect{ sideEffect->
        when(sideEffect){
            SplashSideEffect.NeedAuth -> goToAuth()
            SplashSideEffect.UserIsEmployee -> goToEmployee()
            SplashSideEffect.UserIsManager -> goToManager()
            SplashSideEffect.UserIsOther -> goToOther()
        }
    }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.splash_back),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = maxWidth / 5f),
            contentScale = ContentScale.FillWidth
        )
    }
}