package com.example.tipjar

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class DebugApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Timber
        Timber.plant(Timber.DebugTree())

        // Flipper
        SoLoader.init(this, false)
        /*if (FlipperUtils.shouldEnableFlipper(this)) {
            val flipper = AndroidFlipperClient.getInstance(this)
            flipper.addPlugin(FrescoFlipperPlugin())

            flipper.start()
        }*/
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.addPlugin(DatabasesFlipperPlugin(this))
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.addPlugin(SharedPreferencesFlipperPlugin(this))
            client.start()
        }
        Fresco.initialize(this)
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@DebugApp)
            modules(DI.allModules)
        }
    }
}
