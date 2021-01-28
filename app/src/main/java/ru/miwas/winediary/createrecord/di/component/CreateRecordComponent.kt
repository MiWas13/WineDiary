package ru.miwas.winediary.createrecord.di.component

import dagger.Component
import ru.miwas.winediary.createrecord.CreateRecordFragment
import ru.miwas.winediary.createrecord.di.module.CreateRecordFragmentModule
import ru.miwas.winediary.createrecord.di.module.CreateRecordModule
import ru.miwas.winediary.di.annotations.ScreenScope
import ru.miwas.winediary.di.components.AppComponent

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [
        CreateRecordModule::class,
        CreateRecordFragmentModule::class
    ]
)
internal interface CreateRecordComponent {
    fun inject(fragment: CreateRecordFragment)
}