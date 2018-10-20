package com.example.michal.pram2

import android.graphics.Bitmap

class ImageCutterService {

    /**
     * Given bitmap it cuts it into grid of dim*columns and dim*rows and returns them in array
     */
    fun createBitmaps(sourceBitmap: Bitmap, dim: Int = 4): Array<Bitmap> {

        val singleGridWidth: Int = sourceBitmap.width / dim
        val singleGridHeight: Int = sourceBitmap.height / dim
        val bmpArr = Array<Bitmap>(
                dim*dim,
                {Bitmap.createBitmap(singleGridWidth, singleGridHeight, Bitmap.Config.ARGB_4444)}
        )

        var index = 0
        var i = 0
        while (i < dim)
        {
            var j = 0
            while (j < dim)
            {
                bmpArr[index] = Bitmap.createBitmap(
                        sourceBitmap,
                        (j * singleGridWidth),
                        (i * singleGridHeight),
                        singleGridWidth,
                        singleGridHeight
                )
                j++
                index++
            }
            i++
        }

        return bmpArr
    }
}