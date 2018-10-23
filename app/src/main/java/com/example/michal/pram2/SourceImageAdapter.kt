package com.example.michal.pram2

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast
import java.util.*

const val TAG = "IMAGE_PUZZLE_"

class SourceImageAdapter(private val mContext: Context,
                         private val bitmapArr: Array<Bitmap>) : BaseAdapter() {

    private val size: Int = bitmapArr.size

    private val imageViews: Array<ImageView> by lazy {
        val result: Array<ImageView> = Array<ImageView>(bitmapArr.size, {ImageView(mContext)})
        var i = 0
        while (i < bitmapArr.size) {
            result[i] = makeDraggableImageView(i, orderedToUnordered.get(i)!!)
            i++
        }
        result
    }

    private val orderedToUnordered: HashMap<Int, Int> by lazy {
        var result: HashMap<Int, Int> = HashMap()
        var i = 0
        while (i < size) {
            var rand = Random().nextInt(bitmapArr.size)
            while (result.containsValue(rand)) {
                rand = Random().nextInt(bitmapArr.size)
            }
            result.put(i, rand)
            i++
        }
        result
    }

    override fun getCount(): Int = bitmapArr.size

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = imageViews[position]
        } else {
            imageView = convertView as ImageView
        }
        return imageView
    }

    fun makeDraggableImageView(position: Int, correctPosition: Int): ImageView {
        val imageView = ImageView(mContext)

        imageView.apply {
            tag = TAG + position.toString()
            adjustViewBounds = true
            scaleType = ImageView.ScaleType.FIT_CENTER
            setPadding(2,2,2,2)
            setImageBitmap(bitmapArr[orderedToUnordered[position]!!])


            setOnLongClickListener() {view ->

                val item = ClipData.Item(view.tag as? CharSequence)
                val dragData = ClipData(
                        view.tag as? CharSequence,
                        arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                        item
                )
                val myShadow = MyDragShadowBuilder(this)

                currentlyDraggedItem = orderedToUnordered[position]!!
                view.startDrag(
                        dragData,   // the data to be dragged
                        myShadow,   // the drag shadow builder
                        null,       // no need to use local data
                        0           // flags (not currently used, set to 0)
                )
            }
            setOnDragListener(SourceDragListener(mContext, position, orderedToUnordered[position]!!))
        }
        return imageView
    }

    private class MyDragShadowBuilder(v: View) : View.DragShadowBuilder(v) {

        private val shadow = ColorDrawable(Color.LTGRAY)

        override fun onProvideShadowMetrics(size: Point, touch: Point) {

            val width: Int = view.width / 2
            val height: Int = view.height / 2
            shadow.setBounds(0, 0, width, height)
            size.set(width, height)

            // Sets the touch point's position to be in the middle of the drag shadow
            touch.set(width / 2, height / 2)
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system constructs
        // from the dimensions passed in onProvideShadowMetrics().
        override fun onDrawShadow(canvas: Canvas) {
            // Draws the ColorDrawable in the Canvas passed in from the system.
            shadow.draw(canvas)
        }
    }

    private val mDragListen = View.OnDragListener { v, event ->

        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                true
            }

            DragEvent.ACTION_DRAG_LOCATION ->
                true

            DragEvent.ACTION_DRAG_EXITED -> {
                true
            }

            DragEvent.ACTION_DROP -> {
                false
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                (v as ImageView).clearColorFilter()
                currentlyDraggedItem = -1
                true
            }

            else -> {
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.")
                false
            }
        }
    }



}