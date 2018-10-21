package com.example.michal.pram2

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class DestinationDragListener(private val context: Context, private val position: Int): View.OnDragListener {

    override fun onDrag(v: View, event: DragEvent): Boolean {
        // Handles each of the expected events
        return when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                if (event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    (v as? ImageView)?.setColorFilter(Color.BLUE)
                    v.invalidate()
                    true
                } else {
                    false
                }
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                (v as ImageView).setColorFilter(Color.YELLOW)
                v.invalidate()
                true
            }

            DragEvent.ACTION_DRAG_LOCATION -> {
                true
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                (v as? ImageView)?.setColorFilter(Color.BLUE)
                v.invalidate()
                true
            }

            DragEvent.ACTION_DROP -> {
                if (currentlyDraggedItem == position) {
                    Toast.makeText(this@DestinationDragListener.context, "The drop was handled.", Toast.LENGTH_LONG)
                    (v as? ImageView)?.setColorFilter(Color.GREEN)
                    v.invalidate()
                    true
                } else {
                    false
                }
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                (v as? ImageView)?.clearColorFilter()
                v.invalidate()
                true
            }

            else -> {
                Log.e("destDragList", "Unknown action type received by OnDragListener.")
                false
            }
        }
    }
}