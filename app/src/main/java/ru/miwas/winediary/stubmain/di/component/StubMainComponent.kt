package ru.miwas.winediary.stubmain.di.component

import dagger.Component
import ru.miwas.winediary.di.annotations.ScreenScope
import ru.miwas.winediary.di.components.AppComponent
import ru.miwas.winediary.stubmain.StubMainFragment
import ru.miwas.winediary.stubmain.di.module.StubMainFragmentModule
import ru.miwas.winediary.stubmain.di.module.StubMainModule

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [
        StubMainModule::class,
        StubMainFragmentModule::class
    ]
)
interface StubMainComponent {
    fun inject(fragment: StubMainFragment)
}