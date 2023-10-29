package five.head.proftestium.training.model

import five.head.core.domain.model.training.TestDataModel

sealed class TestState {
    data object Load : TestState()
    data class CurrentQuestion(
        val points: Int,
        val position: Int,
        val numberOfPositions: Int,
        override val name: String,
        override val answers: List<String>,
        override val rightAnswerIndex: Int,
    ): TestDataModel.QuestionModel, TestState()

    data class EndOfTest(
        val name: String,
        val points: Int,
    ) : TestState()
    companion object{
        fun TestDataModel.QuestionModel.fromModelToCurrentQuestion(points: Int, position: Int,  numberOfPositions: Int) = CurrentQuestion(
            points, position, numberOfPositions, name, answers, rightAnswerIndex
        )
    }

}