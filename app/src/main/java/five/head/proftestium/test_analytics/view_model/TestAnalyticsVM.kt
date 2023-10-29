package five.head.proftestium.test_analytics.view_model

import androidx.lifecycle.viewModelScope
import five.head.core.domain.repos.TestAnalyticsRepos
import five.head.proftestium.common.vm.AppVM
import five.head.proftestium.test_analytics.model.TestAnalyticsEvent
import five.head.proftestium.test_analytics.model.TestAnalyticsSideEffect
import five.head.proftestium.test_analytics.model.TestAnalyticsState
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class TestAnalyticsVM(
    override val di: DI
) : AppVM<TestAnalyticsState, TestAnalyticsSideEffect, TestAnalyticsEvent>(), DIAware {
    private val analyticsRepos by di.instance<TestAnalyticsRepos>()
    override val container: Container<TestAnalyticsState, TestAnalyticsSideEffect> = viewModelScope
        .container(TestAnalyticsState()){load()}

    override fun onEvent(event: TestAnalyticsEvent) {
        TODO("Not yet implemented")
    }

    private fun load() = intent {
        val departments = analyticsRepos.getDepartments()
        val dep = departments.randomOrNull() ?: return@intent
        val analyticsDepartment =
            analyticsRepos.getDepartmentAnalytics(dep.id)
        reduce {
            state.copy(
                listDepartment = departments,
                currentDepartment = dep,
                listAnalyticsOnUserTest = analyticsDepartment
            )
        }
    }

    override fun onError(er: Throwable) {
        TODO("Not yet implemented")
    }

}