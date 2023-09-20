package com.yukaida.todo.repositoty

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity
data class TodoData(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "tag") var tag: String = "",
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "must_do") var mustDo: Boolean = false,
    @ColumnInfo(name = "content") var content: String,
    @ColumnInfo(name = "create_time") var createTime: String,
    @ColumnInfo(name = "update_time") var updateTime: String
)