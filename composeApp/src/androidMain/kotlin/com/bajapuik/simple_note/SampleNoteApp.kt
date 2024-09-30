package com.bajapuik.simple_note

import android.app.Application
import com.bajapuik.simple_note.base.CoreApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class SampleNoteApp : Application() {
    override fun onCreate() {
        super.onCreate()
        CoreApplication.initialize {
            androidContext(this@SampleNoteApp)
            androidLogger()
        }
    }
}