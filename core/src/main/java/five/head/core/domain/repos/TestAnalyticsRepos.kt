package five.head.core.domain.repos

import five.head.core.domain.model.analytics.AnalyticsOnUserTestModel
import five.head.core.domain.model.analytics.DepartmentInfoModel
import five.head.core.domain.model.analytics.TestAnalyticsDepartmentModel

interface TestAnalyticsRepos {
    suspend fun getDepartments() : List<DepartmentInfoModel>
    suspend fun getDepartmentAnalytics(id:String) : List<TestAnalyticsDepartmentModel>
    suspend fun getAnalyticsOnUserTests(id: String) : List<AnalyticsOnUserTestModel>
}