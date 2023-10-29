package five.head.core.data.model.training

import five.head.core.domain.model.training.TestInfoModel
import kotlinx.serialization.Serializable

@Serializable
internal data class TestInfoBody(
    override val name: String, override val id: String
): TestInfoModel