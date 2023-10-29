package five.head.core.dependency_injection

import five.head.core.data.repos.AuthReposImpl
import five.head.core.data.repos.ProfileUserReposImpl
import five.head.core.data.repos.TestAnalyticsReposImpl
import five.head.core.data.repos.TrainingReposImpl
import five.head.core.data.repos.UserReposImpl
import five.head.core.domain.repos.AuthRepos
import five.head.core.domain.repos.ProfileUserRepos
import five.head.core.domain.repos.TestAnalyticsRepos
import five.head.core.domain.repos.TrainingRepos
import five.head.core.domain.repos.UserRepos
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.new

internal val ReposModule by DI.Module{
    bindSingleton<UserRepos> { new(::UserReposImpl) }
    bindProvider<AuthRepos> { new(::AuthReposImpl) }
    bindProvider<ProfileUserRepos> { new(::ProfileUserReposImpl) }
    bindProvider<TrainingRepos> { new(::TrainingReposImpl) }
    bindProvider<TestAnalyticsRepos> { new(::TestAnalyticsReposImpl) }
}