package com.example.michal.pram2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playBtn: Button = findViewById(R.id.play_btn)
        playBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, PuzzleActivity::class.java)
            startActivity(intent)
        })
    }
}
