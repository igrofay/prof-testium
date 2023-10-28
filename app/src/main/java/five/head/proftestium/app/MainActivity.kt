package five.head.proftestium.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import five.head.proftestium.auth.view.AuthScreen
import five.head.proftestium.common.ui.theme.AppTheme
import five.head.proftestium.nav.view.EmployeeContentNav
import five.head.proftestium.splash.view.SplashScreen
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
                EmployeeContentNav()
            }
        }
    }
}