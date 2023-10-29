package five.head.proftestium.test_analytics.model

import five.head.core.domain.model.analytics.DepartmentInfoModel

sealed class TestAnalyticsEvent{
    data class DepartmentSelected(val department: DepartmentInfoModel) : TestAnalyticsEvent()
    data object BackToUsers : TestAnalyticsEvent()
    data class LoadAnalyticsOnUserTest(val id: String) : TestAnalyticsEvent()
}
