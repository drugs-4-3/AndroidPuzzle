package com.example.michal.pram2

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast

class DestinationImageAdapter(
        private val mContext: Context,
        private val size: Int,
        private val imgArr: Array<Bitmap>): BaseAdapter() {

    override fun getCount(): Int = size

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = destinationImages[position]
        } else {
            imageView = convertView as ImageView
        }
        return imageView
    }

    private val destinationImages: Array<ImageView> by lazy {
        var result = Array<ImageView>(size, {ImageView(mContext)})
        var i = 0
        while (i < size) {
            result[i] = getEmptyView(i)
            i++
        }
        result
    }

    fun getEmptyView(position: Int): ImageView {
        val imageView: ImageView = ImageView(mContext)

        imageView.apply() {
            adjustViewBounds = true
            scaleType = ImageView.ScaleType.FIT_CENTER
            setPadding(2,2,2,2)

            val bitmap: Bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888)
            bitmap.eraseColor(Color.GRAY)
            setImageBitmap(bitmap)

            setOnDragListener(DestinationDragListener(mContext, position, imgArr[position]))
        }

        return imageView
    }
}