package five.head.core.data.model.training

import kotlinx.serialization.Serializable

@Serializable
internal data class ResultTest(
    val testId: String,
    val rightCountAnswer: Int,
)