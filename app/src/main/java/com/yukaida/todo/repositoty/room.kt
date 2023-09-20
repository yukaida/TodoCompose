package com.yukaida.todo.repositoty

import android.app.Application
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

@Dao
interface TodoDao {
    @Query("SELECT *FROM tododata")
    fun getAll(): List<TodoData>

    @Insert()
    fun insert(vararg todoData: TodoData)

}

fun getTodoDao(applicationContext: Application): TodoDao {
    val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "TodoAppDatabase"
    ).build()

    return db.todoDao()
}