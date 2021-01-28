package ru.miwas.winediary.main.di.component

import dagger.Component
import ru.miwas.winediary.di.annotations.ActivityScope
import ru.miwas.winediary.di.components.AppComponent
import ru.miwas.winediary.main.MainActivity
import ru.miwas.winediary.main.di.module.MainActivityModule
import ru.miwas.winediary.main.di.module.MainModule

@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [
        MainModule::class,
        MainActivityModule::class
    ]
)
interface MainComponent {
    fun inject(activity: MainActivity)
}