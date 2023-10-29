package five.head.proftestium.training.model

import five.head.core.domain.model.training.DocumentInfoModel
import five.head.core.domain.model.training.TestInfoModel

data class LecturesState(
    val documents: List<DocumentInfoModel> = listOf(),
    val tests: List<TestInfoModel> = listOf()
)