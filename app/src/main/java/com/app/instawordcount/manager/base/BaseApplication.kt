package com.app.instawordcount.manager.base

import android.app.Application
import com.app.instawordcount.di.apiModule
import com.app.instawordcount.di.viewModelModule
import com.app.instawordcount.manager.db.WordRepository
import com.app.instawordcount.manager.db.WordRoomDatabase
import com.instabug.bug.BugReporting
import com.instabug.chat.Replies
import com.instabug.library.Instabug
import com.instabug.library.invocation.InstabugInvocationEvent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {
    private val database by lazy { WordRoomDatabase.getDatabase(this) }
    val repository by lazy { WordRepository(database.wordDao()) }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApplication)
            modules(listOf(apiModule, viewModelModule))
        }

        //INISTABUG
        Instabug.Builder(this, "APP_TOKEN")
            .setInvocationEvents(
                InstabugInvocationEvent.SHAKE,
                InstabugInvocationEvent.FLOATING_BUTTON
            )
            .build()
        BugReporting.setInvocationEvents(
            InstabugInvocationEvent.SHAKE,
            InstabugInvocationEvent.SCREENSHOT
        )
        Instabug.show()
        BugReporting.show(BugReporting.ReportType.BUG)
        BugReporting.show(BugReporting.ReportType.FEEDBACK)
        Replies.show()


    }
}