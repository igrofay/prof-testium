package five.head.core.data.model.analytics

import five.head.core.domain.model.analytics.DepartmentInfoModel
import kotlinx.serialization.Serializable

@Serializable
internal data class DepartmentInfoBody(
    override val id: String, override val name: String
) : DepartmentInfoModel