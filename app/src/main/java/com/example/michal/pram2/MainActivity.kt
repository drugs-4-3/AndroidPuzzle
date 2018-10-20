package com.example.michal.pram2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

const val DRAWABLE_RESOURCE_MSG = "DRAWABLE_RESOURCE_MSG"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playBtn: Button = findViewById(R.id.play_btn)
        playBtn.setOnClickListener() {
            val intent = Intent(this, PuzzleActivity::class.java).apply {
                putExtra(DRAWABLE_RESOURCE_MSG, Integer.toString(R.drawable.jeleniagora))
            }
            startActivity(intent)
        }
    }
}
