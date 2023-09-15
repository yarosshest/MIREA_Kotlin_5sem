package com.example.work4.modules

import com.example.work4.authorization.RegisterFragment
import dagger.Component

//@Component(modules = [RoomModule::class, AppModule::class])
@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {
    fun inject(registerFragment : RegisterFragment)

}