package five.head.proftestium.training.view_model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import five.head.core.domain.model.training.TestDataModel
import five.head.core.domain.repos.TrainingRepos
import five.head.proftestium.common.vm.AppVM
import five.head.proftestium.training.model.TestEvent
import five.head.proftestium.training.model.TestState
import five.head.proftestium.training.model.TestState.Companion.fromModelToCurrentQuestion
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class TestScreenVM(
    savedStateHandle: SavedStateHandle,
    override val di: DI
) : AppVM<TestState, Any, TestEvent>(), DIAware {
    private var points = 0
    private var currentQuestion = -1
    private lateinit var testDataModel: TestDataModel
    private val idTest = savedStateHandle.get<String>("id_test")!!
    private val trainingRepos by di.instance<TrainingRepos>()
    override val container: Container<TestState, Any> = viewModelScope
        .container(TestState.Load) {
            loadTest()
        }

    override fun onEvent(event: TestEvent) {
        when(event){
            is TestEvent.Answer -> answerSelected(event.index)
        }
    }

    private fun loadTest() = intent {
        runCatching { trainingRepos.getTest(idTest) }
            .onSuccess { data ->
                testDataModel = data
                nextQuestion()
            }
            .onFailure(::onError)
    }

    private fun nextQuestion() = intent {
        currentQuestion++
        if (currentQuestion < testDataModel.questions.size)
            reduce {
                testDataModel
                    .questions[currentQuestion]
                    .fromModelToCurrentQuestion(
                        points = points,
                        position = currentQuestion.inc(),
                        numberOfPositions = testDataModel.questions.size
                    )
            }
        else{
            saveResult()
            reduce {
                TestState.EndOfTest(
                    name = testDataModel.name,
                    points = points
                )
            }
        }
    }
    private fun answerSelected(index: Int) = intent{
        val tState = (state as? TestState.CurrentQuestion) ?: return@intent
        if (index == tState.rightAnswerIndex) points++
        nextQuestion()
    }
    private fun saveResult() = intent {
        runCatching {
            trainingRepos.saveResultTest(testDataModel.id, points)
        }.onFailure(::onError)
    }
    override fun onError(er: Throwable) {
        Log.e("TestScreenVM", er.message.toString())
    }
}