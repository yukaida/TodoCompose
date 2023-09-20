package com.yukaida.todo

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.yukaida.todo.repositoty.AppDatabase
import com.yukaida.todo.repositoty.TodoDao
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class TodoApplication @Inject constructor() : Application() {
    private var db: AppDatabase? = null
    override fun onCreate() {
        super.onCreate()

        Log.d("TodoApplication", "onCreate: ")
    }

    public fun getTodoDao(): TodoDao {
        return if (db == null) {
            db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "TodoAppDatabase"
            ).build()
            db!!.todoDao()
        } else {
            db!!.todoDao()
        }
    }

}