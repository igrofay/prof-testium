package five.head.proftestium.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import five.head.proftestium.R
import five.head.proftestium.common.ui.appbar.AppBar
import five.head.proftestium.start.auth.view.AuthScreen
import five.head.proftestium.common.ui.theme.AppTheme
import five.head.proftestium.nav.view.AppNav
import five.head.proftestium.nav.view.EmployeeContentNav
import five.head.proftestium.start.splash.view.SplashScreen
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI

class MainActivity: ComponentActivity(), DIAware {
    override val di: DI by closestDI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme{
                AppNav()
            }
        }
    }
}