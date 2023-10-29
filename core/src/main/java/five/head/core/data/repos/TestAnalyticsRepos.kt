package five.head.core.data.repos

import five.head.core.data.data_source.network.DepartmentApi
import five.head.core.data.data_source.network.TestApi
import five.head.core.data.model.analytics.AnalyticsOnUserTestBody
import five.head.core.data.model.analytics.DepartmentInfoBody
import five.head.core.data.model.analytics.TestAnalyticsDepartmentBody
import five.head.core.domain.model.analytics.AnalyticsOnUserTestModel
import five.head.core.domain.model.analytics.DepartmentInfoModel
import five.head.core.domain.model.analytics.TestAnalyticsDepartmentModel
import five.head.core.domain.repos.TestAnalyticsRepos
import io.ktor.client.call.body

internal class TestAnalyticsReposImpl(
    private val departmentApi: DepartmentApi,
    private val testApi: TestApi,
) : TestAnalyticsRepos {
    override suspend fun getDepartments(): List<DepartmentInfoModel> =
        departmentApi.getDepartment().body<List<DepartmentInfoBody>>()

    override suspend fun getDepartmentAnalytics(id: String): List<TestAnalyticsDepartmentModel> =
        testApi.getDepartmentAnalytics(id).body<List<TestAnalyticsDepartmentBody>>()
    override suspend fun getAnalyticsOnUserTests(id: String): List<AnalyticsOnUserTestModel> =
        testApi.getAnalyticsOnUserTests(id).body<List< AnalyticsOnUserTestBody>>()
}