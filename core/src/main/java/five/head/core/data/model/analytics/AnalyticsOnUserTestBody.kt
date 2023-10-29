package five.head.core.data.model.analytics

import five.head.core.domain.model.analytics.AnalyticsOnUserTestModel
import kotlinx.serialization.Serializable

@Serializable
internal data class AnalyticsOnUserTestBody(
    override val testName: String, override val countPoints: Int, override val maxCountPoints: Int

) : AnalyticsOnUserTestModel