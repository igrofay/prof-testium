package five.head.proftestium.training.view_model

import android.util.Log
import androidx.lifecycle.viewModelScope
import five.head.core.domain.repos.TrainingRepos
import five.head.proftestium.common.vm.AppVM
import five.head.proftestium.training.model.LecturesState
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class LecturesVM(
    override val di: DI
) : AppVM<LecturesState, Any, Any>(), DIAware{
    private val trainingRepos by instance<TrainingRepos>()
    override val container: Container<LecturesState, Any> = viewModelScope
        .container(LecturesState()){
            loadDocuments()
            loadTests()
        }

    override fun onEvent(event: Any) {

    }

    private fun loadDocuments() = intent {
        runCatching { trainingRepos.getDocuments() }
            .onSuccess { list->
                reduce {
                    state.copy(documents = list)
                }
            }
            .onFailure(::onError)
    }
    private fun loadTests() = intent {
        runCatching { trainingRepos.getTests() }
            .onSuccess { list->
                reduce {
                    state.copy(tests = list)
                }
            }
            .onFailure(::onError)
    }

    override fun onError(er: Throwable) {
        Log.e("LecturesVM", er.message.toString())
    }
}