package five.head.core.data.model.training

import five.head.core.domain.model.training.TestDataModel
import kotlinx.serialization.Serializable

@Serializable
internal data class TestDataBody(
    override val id: String,
    override val name: String,
    override val questions: List<QuestionBody>
): TestDataModel{
    @Serializable
    data class QuestionBody(
        override val name: String,
        override val answers: List<String>,
        override val rightAnswerIndex: Int
    ): TestDataModel.QuestionModel
}