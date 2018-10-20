package com.example.michal.pram2

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class ImageAdapter(private val mContext: Context,
                   private val bitmapArr: Array<Bitmap>) : BaseAdapter() {

    override fun getCount(): Int = bitmapArr.size

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(mContext)
            imageView.adjustViewBounds = true
            imageView.scaleType = ImageView.ScaleType.FIT_CENTER
            imageView.setPadding(2,2,2,2)
        } else {
            imageView = convertView as ImageView
        }

        imageView.setImageBitmap(bitmapArr[position])
        return imageView
    }
}