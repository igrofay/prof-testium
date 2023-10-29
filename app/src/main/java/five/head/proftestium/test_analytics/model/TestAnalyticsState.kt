package five.head.proftestium.test_analytics.model

import five.head.core.domain.model.analytics.AnalyticsOnUserTestModel
import five.head.core.domain.model.analytics.DepartmentInfoModel
import five.head.core.domain.model.analytics.TestAnalyticsDepartmentModel

data class TestAnalyticsState(
    val listDepartment: List<DepartmentInfoModel> = listOf(),
    val currentDepartment: DepartmentInfoModel? = null,
    val listAnalyticsDepartment: List<TestAnalyticsDepartmentModel> = listOf(),
    val listAnalyticsOnUserTest: List<AnalyticsOnUserTestModel> = listOf(),
)