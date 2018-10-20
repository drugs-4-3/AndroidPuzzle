package com.example.michal.pram2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import kotlinx.android.synthetic.main.activity_puzzle.*

class PuzzleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puzzle)

        val sourceGrid: GridView = findViewById<GridView>(R.id.sourceGrid)
        sourceGrid.adapter = ImageAdapter(this, getImgArr())
    }

    fun getImgArr(): Array<Bitmap> {
        val msg: String = intent.getStringExtra(DRAWABLE_RESOURCE_MSG)
        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, msg.toInt())
        val imgCutterService = ImageCutterService()

        return imgCutterService.createBitmaps(bitmap)
    }
}
