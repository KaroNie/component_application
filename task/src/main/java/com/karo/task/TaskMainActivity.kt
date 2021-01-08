package com.karo.task

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.karo.common.BaseActivity
import com.karo.common.BaseApplication
import com.karo.router_annotation.Route
import kotlinx.android.synthetic.main.activity_task_main.*

@Route(path = "task/task")
class TaskMainActivity : BaseActivity() {

    private lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_main)
        val isApplication = BaseApplication.isApplication()
        Log.d("TaskMainActivity", isApplication.toString())

        taskViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(TaskViewModel::class.java)

        taskViewModel.initData()
        tv_content.visibility = View.VISIBLE
        tv_content.append(taskViewModel.liveData.value)
    }

    var counter = 0
    fun happy(view: View) {
        taskViewModel.liveData.postValue(tv_content.text.toString())
        counter++
    }
}
