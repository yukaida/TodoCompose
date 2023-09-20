package com.yukaida.todo.ui.home

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yukaida.todo.MainActivity
import com.yukaida.todo.TodoApplication
import com.yukaida.todo.repositoty.TodoDao
import com.yukaida.todo.repositoty.TodoData
import com.yukaida.todo.repositoty.getTodoDao
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
//@AndroidEntryPoint
class HomeViewModel @Inject constructor(private val application: TodoApplication) :
    AndroidViewModel(application) {
    //class HomeViewModel( val application: TodoApplication) : AndroidViewModel(application) {

    private val _todoList = MutableSharedFlow<MutableList<TodoData>>()
    val todoListMsf: SharedFlow<MutableList<TodoData>>
        get() = _todoList

    init {
        val todoListTemp = mutableStateListOf<TodoData>()

        viewModelScope.launch {
            repeat(50) {
                delay(10)
                todoListTemp.add(createTodoData())
            }
            _todoList.emit(todoListTemp)
            Log.d("TodoApp _todoList emit", ": ${todoListTemp.toList()}")
        }

    }

    private fun createTodoData() = TodoData(
        0,
        content = "测试内容",
        createTime = System.currentTimeMillis().toString(),
        updateTime = System.currentTimeMillis().toString()
    )

    //------------------------
    fun getTodoList() {

////        val todoDao = getTodoDao(application)
//        val todoDao = application.getTodoDao()
//        Log.d("HomeViewModel todoDao", "初始: $todoDao")
//
//        viewModelScope.launch(Dispatchers.IO) {
////            delay(5000)
//
//
//
//
//            val todoList = todoDao.getAll()
//            Log.d("HomeViewModel", "初始: $todoList")

//            todoListMSF.emit(todoList)
//            repeat(5) {
//                todoDao.insert(createTodoData())
//                delay(800)
//                Log.d("HomeViewModel", "更新 ${todoDao.getAll()}")
//            }
//        }
    }

}