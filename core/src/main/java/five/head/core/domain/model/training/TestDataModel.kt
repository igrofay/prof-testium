package five.head.core.domain.model.training

interface TestDataModel {
    val id: String
    val name: String
    val questions: List<QuestionModel>
    interface QuestionModel{
        val name: String
        val answers: List<String>
        val rightAnswerIndex: Int
    }
}