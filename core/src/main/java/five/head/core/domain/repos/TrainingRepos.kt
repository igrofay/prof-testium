package five.head.core.domain.repos

import five.head.core.domain.model.training.DocumentInfoModel
import five.head.core.domain.model.training.TestDataModel
import five.head.core.domain.model.training.TestInfoModel

interface TrainingRepos {
    suspend fun getDocuments() : List<DocumentInfoModel>
    suspend fun getTests(): List<TestInfoModel>

    suspend fun getTest(id: String): TestDataModel
    suspend fun saveResultTest(idTest: String, rightCountAnswer: Int)
}