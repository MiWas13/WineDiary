package ru.miwas.winediary.homelist.di.component

import dagger.Component
import ru.miwas.winediary.di.annotations.ScreenScope
import ru.miwas.winediary.di.components.AppComponent
import ru.miwas.winediary.homelist.HomeListFragment
import ru.miwas.winediary.homelist.di.module.HomeListFragmentModule
import ru.miwas.winediary.homelist.di.module.HomeListModule

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [
        HomeListModule::class,
        HomeListFragmentModule::class
    ]
)
interface HomeListComponent {
    fun inject(fragment: HomeListFragment)
}