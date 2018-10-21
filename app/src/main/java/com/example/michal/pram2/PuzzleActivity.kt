package com.example.michal.pram2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView

/**
 * index of puzzle that is currently being dragged by user
 * if index in sourceGrid is equal to index in destGrid - then drop can be accepted
 */
var currentlyDraggedItem = -1

class PuzzleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puzzle)

        val sourceGrid: GridView = findViewById<GridView>(R.id.sourceGrid)
        sourceGrid.adapter = SourceImageAdapter(this, getImgArr())

        val destGrid: GridView = findViewById<GridView>(R.id.destinationGrid)
        destGrid.adapter = DestinationImageAdapter(this, 16)
    }

    // ogarnac design widoku -> zeby source i dest zajmowaly po polowe miejsca !!!


    fun getImgArr(): Array<Bitmap> {
        val msg: String = intent.getStringExtra(DRAWABLE_RESOURCE_MSG)
        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, msg.toInt())
        val imgCutterService = ImageCutterService()

        return imgCutterService.createBitmaps(bitmap)
    }
}
