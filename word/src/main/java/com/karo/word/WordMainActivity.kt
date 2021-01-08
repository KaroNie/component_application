package com.karo.word

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karo.router_annotation.Route

@Route(path = "word/word")
class WordMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_main)
    }
}
