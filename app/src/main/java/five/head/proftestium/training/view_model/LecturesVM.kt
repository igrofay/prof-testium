package five.head.proftestium.training.view_model

import androidx.lifecycle.viewModelScope
import five.head.proftestium.common.vm.AppVM
import five.head.proftestium.training.model.LecturesState
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container

class LecturesVM(
    override val di: DI
) : AppVM<LecturesState, Any, Any>(), DIAware{

    override val container: Container<LecturesState, Any> = viewModelScope
        .container(LecturesState())

    override fun onEvent(event: Any) {

    }
    override fun onError(er: Throwable) {

    }
}