package five.head.core.data.model.training

import five.head.core.domain.model.training.DocumentInfoModel
import kotlinx.serialization.Serializable

@Serializable
internal data class DocumentInfoBody(
    override val name: String, override val urlFile: String
): DocumentInfoModel