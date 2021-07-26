package com.suribetpos.main.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.suribetpos.R
import java.io.ByteArrayOutputStream
import java.util.*


/**
 * Created by Velmurugan on 2/19/2021.
 */
class BarcodeGeneration {
    init {
        val instance = BarcodeGeneration()
    }

    companion object {
        @Throws(WriterException::class)
        fun encodeAsBitmap(
            context: Context,
            contents: String?,
            format: BarcodeFormat,
            img_width: Int,
            img_height: Int
        ): Bitmap? {
            if (contents == null) {
                return null
            }
            var hints: MutableMap<EncodeHintType?, Any?>? = null
            val encoding = guessAppropriateEncoding(contents)
            if (encoding != null) {
                hints = EnumMap(EncodeHintType::class.java)
                hints!![EncodeHintType.CHARACTER_SET] = encoding
            }
            val writer = MultiFormatWriter()
            val result: BitMatrix = try {
                writer.encode(contents, format, img_width, img_height, hints)
            } catch (iae: IllegalArgumentException) {
                // Unsupported format
                return null
            }
            val width = result.width
            val height = result.height
            val pixels = IntArray(width * height)
            for (y in 0 until height) {
                val offset = y * width
                for (x in 0 until width) {
                    pixels[offset + x] =
                        if (result[x, y]) context.resources.getColor(R.color.color_black) else context.resources.getColor(
                            R.color.color_whit
                        )
                }
            }
            val bitmap: Bitmap = Bitmap.createBitmap(
                width, height,
                Bitmap.Config.ARGB_8888
            )
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
            return bitmap
        }

        private fun guessAppropriateEncoding(contents: CharSequence): String? {
            // Very crude at the moment
            for (element in contents) {
                if (element.toInt() > 0xFF) {
                    return "UTF-8"
                }
            }
            return null
        }

        fun getBitmap(context: Context, barcode: String?, width: Int, height: Int): Bitmap? {
            var barcodeBitmap: Bitmap? = null
            try {
                val bitmap1 = encodeAsBitmap(context,barcode, BarcodeFormat.CODE_128, width, height)
                val bytearrayoutputstream = ByteArrayOutputStream()
                bitmap1!!.compress(Bitmap.CompressFormat.JPEG, 40, bytearrayoutputstream)
                val BYTE: ByteArray = bytearrayoutputstream.toByteArray()
                //barcodeBitmap = BitmapFactory.decodeByteArray(BYTE, 0, BYTE.size)
                barcodeBitmap = bitmap1
            } catch (e: WriterException) {
                e.printStackTrace()
            }
            return barcodeBitmap
        }
    }
}