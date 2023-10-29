package five.head.core.data.model.analytics

import five.head.core.domain.model.analytics.TestAnalyticsDepartmentModel
import kotlinx.serialization.Serializable

@Serializable
internal data class TestAnalyticsDepartmentBody(
    override val fullname: String, override val userId: String, override val countPoints: Int
) : TestAnalyticsDepartmentModel