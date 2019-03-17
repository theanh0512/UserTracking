package pham.honestbee.usertracking

import android.databinding.DataBindingUtil
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pham.honestbee.usertracking.binding.AppDataBindingComponent
import pham.honestbee.usertracking.di.DaggerAppComponent

/**
 * Created by Pham on 25/8/2018.
 */
class UserTrackingApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        DataBindingUtil.setDefaultComponent(AppDataBindingComponent())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
