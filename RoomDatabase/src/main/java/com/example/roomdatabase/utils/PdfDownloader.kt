package com.example.roomdatabase.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Picture
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.webkit.WebView
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PdfDownloader {
    fun downloadPdf(webView: WebView, pdfFileName: String, context: Context) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(webView.width, webView.height, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        // Draw the WebView content onto the PDF page
        val canvas: Canvas = page.canvas
        drawWebView(webView, canvas)

        pdfDocument.finishPage(page)

        // Save the PDF to a file
        val pdfFile = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            pdfFileName
        )
        try {
            pdfDocument.writeTo(FileOutputStream(pdfFile))
//            at ${pdfFile.absolutePath}
            Toast.makeText(context, "PDF saved successfully", Toast.LENGTH_SHORT)
                .show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "Error saving PDF", Toast.LENGTH_SHORT).show()
        } finally {
            pdfDocument.close()
        }
    }

    private fun drawWebView(webView: WebView, canvas: Canvas) {
        val picture: Picture = webView.capturePicture()
        val bitmap = Bitmap.createBitmap(picture.width, picture.height, Bitmap.Config.ARGB_8888)
        picture.draw(canvas)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
    }
}