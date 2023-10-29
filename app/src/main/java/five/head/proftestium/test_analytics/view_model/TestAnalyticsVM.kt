package five.head.proftestium.test_analytics.view_model

import android.util.Log
import androidx.lifecycle.viewModelScope
import five.head.core.domain.model.analytics.DepartmentInfoModel
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
        when(event){
            is TestAnalyticsEvent.DepartmentSelected -> loadAnalyticsDepartment(event.department)
            TestAnalyticsEvent.BackToUsers -> intent {
                reduce {
                    state.copy(
                        listAnalyticsOnUserTest = listOf()
                    )
                }
            }

            is TestAnalyticsEvent.LoadAnalyticsOnUserTest -> intent {
                runCatching {
                    analyticsRepos.getAnalyticsOnUserTests(event.id)
                }.onSuccess {
                    reduce {
                        state.copy(
                            listAnalyticsOnUserTest = it
                        )
                    }
                }.onFailure(::onError)
            }
        }
    }

    private fun load() = intent {
        runCatching {
            val departments = analyticsRepos.getDepartments()
            if (departments.isEmpty()) return@intent
            val department = departments.first()
            reduce {
                state.copy(
                    listDepartment = departments,
                    currentDepartment = department
                )
            }
            loadAnalyticsDepartment(department)
        }.onFailure(::onError)
    }

    private fun loadAnalyticsDepartment(department: DepartmentInfoModel) = intent {
        runCatching {
            val analyticsDepartment =
                analyticsRepos.getDepartmentAnalytics(department.id)
            reduce {
                state.copy(
                    listAnalyticsDepartment = analyticsDepartment,
                    currentDepartment = department,
                )
            }
        }
    }
    override fun onError(er: Throwable) {
        Log.e("TestAnalyticsVM", er.message.toString())
    }

}