package com.example.michal.pram2

import android.content.ClipDescription
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class SourceDragListener(
        private val context: Context,
        private val position: Int,
        private val correctPosition: Int): View.OnDragListener {

    override fun onDrag(v: View, event: DragEvent): Boolean {
        return when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                if (event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    true
                } else {
                    false
                }
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                true
            }

            DragEvent.ACTION_DRAG_LOCATION -> {
                true
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                true
            }

            DragEvent.ACTION_DROP -> {
                false
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                if (event.result && currentlyDraggedItem == correctPosition) {
                    (v as ImageView).setColorFilter(Color.GRAY)
                    v.invalidate()
                }
                true
            }

            else -> {
                Log.e("destDragList", "Unknown action type received by OnDragListener.")
                false
            }
        }
    }
}