package com.karo.componentapplication

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.karo.arouter.ARouter
import com.karo.common.BaseActivity
import com.karo.common.utils.CacheUtils
import com.karo.common.utils.ToastUtils
import com.karo.common.utils.dp2dx

class LoginActivity : BaseActivity(), View.OnClickListener {
    private val usernameKey = "username"
    private val passwordKey = "password"

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById(R.id.et_username)
        password = findViewById(R.id.et_password)
        button = findViewById(R.id.bt_login)

        button.setOnClickListener(this)

        username.setText(CacheUtils.getString(usernameKey))
        password.setText(CacheUtils.getString(passwordKey))

        val layoutParams = button.layoutParams
        layoutParams.height = dp2dx(60f).toInt()
        button.layoutParams = layoutParams
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_login -> {
                login()
            }
        }
    }

    private fun verify(username: String?, password: String?): Boolean {
        if (username == null || username.length != 11) {
            ToastUtils.toast("用户名错误", Toast.LENGTH_SHORT, Gravity.CENTER)
            return false
        }
        if (password == null || password.length < 6) {
            ToastUtils.toast("密码错误", Toast.LENGTH_SHORT, Gravity.CENTER)
            return false
        }

        return true
    }

    private fun login() {
        val userName = username.text.toString()
        val passWord = password.text.toString()
        if (verify(userName, passWord)) {
            CacheUtils.saveString(usernameKey, userName)
            CacheUtils.saveString(passwordKey, passWord)

            ARouter.jumpActivity("main/main", null)
            finish()
        }
    }

}
