package com.karo.componentapplication

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.karo.arouter.ARouter
import com.karo.common.BaseActivity
import com.karo.common.LiveDataHelper
import com.karo.common.utils.ToastUtils
import com.karo.common.utils.px
import com.karo.common.widgets.CountDownEvaluator
import com.karo.router_annotation.Route
import kotlinx.android.synthetic.main.activity_customer_view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Route(path = "main/main")
class MainActivity : BaseActivity() {

    private lateinit var liveData: MutableLiveData<String>
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_view)
        liveData = LiveDataHelper.whit("eat", String::class.java)
        liveData.observe(this, {
            ToastUtils.toast(it)
            Log.d(MainActivity::class.simpleName, it)
        }
        )

        initData()

//        animate()

//        countDownAnimate()

    }

    private fun initData() {
        liveData.value = "hello~~今晚上一起吃饭阿里角度看"
    }

    private fun countDownAnimate() {
        val textAnimate = ObjectAnimator.ofObject(view, "textContent", CountDownEvaluator(),"1")
        textAnimate.duration = 10000
        val textSizeAnimate = ObjectAnimator.ofFloat(view, "textSize", 60f.px)
        textSizeAnimate.duration = 1000

        val animatorSet = AnimatorSet()
        animatorSet.startDelay = 1000
        animatorSet.playTogether(textAnimate, textSizeAnimate)
        animatorSet.start()
    }

    private fun animate() {
//        val animator = ObjectAnimator.ofFloat(view, "radius", 150f.px)
//        animator.startDelay = 1000
//        animator.duration = 2000
//        animator.start()

    }

    fun jumpToTask(view: View) {
        ARouter.jumpActivity("task/task", null)
//        liveData.postValue("hello！！今晚上一起吃饭:$counter")
//        counter++
//        handler()
        /*GlobalScope.launch(Dispatchers.Main) {
//            val data = getData()
//            val processedData = processData(data)
//            textView.text = processedData

            val data1 = async { getData() }
            val data2 = async { processData("hen_coder") }

            textView.text = "${data1.await()}--${data2.await()}"
        }*/
    }

    // 耗时函数 1
    private suspend fun getData(): String = withContext(Dispatchers.IO) {
        // 假设这个函数比较耗时，需要放在后台
        Log.d("MainActivity", "getData ${Thread.currentThread().name}")

        return@withContext "hen_coder"
    }

    // 耗时函数 2
    private suspend fun processData(data: String): String = withContext(Dispatchers.IO) {
        // 假设这个函数也比较耗时，需要放在后台
        Log.d("MainActivity", "processData ${Thread.currentThread().name}")
        return@withContext data.split("_") // 把 "hen_coder" 拆成 ["hen", "coder"]
            .map { it.capitalize() } // 把 ["hen", "coder"] 改成 ["Hen", "Coder"]
            .reduce { acc, s -> acc + s } // 把 ["Hen", "Coder"] 改成 "HenCoder"
    }

    private var handler: Handler? = null

    private fun handler() {
        Thread {
            Looper.prepare()
            handler = object : Handler(Looper.myLooper()) {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    val obj = msg.obj
                    liveData.postValue(obj.toString())
                }
            }
            Looper.loop()

            val message = Message.obtain()
            message.obj = "长江长江，我是黄河！！"
            handler!!.sendMessage(message)
        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
