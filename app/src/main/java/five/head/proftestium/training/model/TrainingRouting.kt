package five.head.proftestium.training.model

import five.head.proftestium.common.model.AppRouting

sealed class TrainingRouting(route: String) :AppRouting(route) {
    data object Lectures: TrainingRouting("${route}_lectures")
    data object Test: TrainingRouting("${route}_test"){
        const val idTest = "id_test"
        fun allRoute() = "${route}/{id_test}"
        fun allRoute(id: String) =  "${route}/$id"
    }
    companion object{
        const val route = "training_routing"
    }
}