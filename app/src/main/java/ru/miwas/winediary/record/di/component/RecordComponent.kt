package ru.miwas.winediary.record.di.component

import dagger.Component
import ru.miwas.winediary.di.annotations.ScreenScope
import ru.miwas.winediary.di.components.AppComponent
import ru.miwas.winediary.record.RecordFragment
import ru.miwas.winediary.record.di.module.RecordFragmentModule
import ru.miwas.winediary.record.di.module.RecordModule

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [
        RecordModule::class,
        RecordFragmentModule::class
    ]
)
interface RecordComponent {
    fun inject(fragment: RecordFragment)
}