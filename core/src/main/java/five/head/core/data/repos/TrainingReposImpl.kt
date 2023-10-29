package five.head.core.data.repos

import five.head.core.data.data_source.network.LecternApi
import five.head.core.data.data_source.network.TestApi
import five.head.core.data.model.training.DocumentInfoBody
import five.head.core.data.model.training.ResultTest
import five.head.core.data.model.training.TestDataBody
import five.head.core.data.model.training.TestInfoBody
import five.head.core.domain.model.training.DocumentInfoModel
import five.head.core.domain.model.training.TestDataModel
import five.head.core.domain.model.training.TestInfoModel
import five.head.core.domain.repos.TrainingRepos
import io.ktor.client.call.body

internal class TrainingReposImpl(
    private val testApi: TestApi,
    private val lecternApi: LecternApi,
) : TrainingRepos {
    override suspend fun getDocuments(): List<DocumentInfoModel> =
        lecternApi.getListLectern().body<List<DocumentInfoBody>>()
    override suspend fun getTests(): List<TestInfoModel> =
        testApi.getListTest().body<List<TestInfoBody>>()

    override suspend fun getTest(id: String): TestDataModel=
        testApi.getTestData(id).body<TestDataBody>()

    override suspend fun saveResultTest(idTest: String, rightCountAnswer: Int) {
        testApi.saveResultTest(ResultTest(idTest, rightCountAnswer))
    }
}