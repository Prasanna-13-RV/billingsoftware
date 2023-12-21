package com.example.billgenerator.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import com.example.roomdatabase.data.entity.bill1.BillEntity1
import com.example.billgenerator.databinding.ActivityPreviewBinding
import com.example.billgenerator.ui.files.HtmlContent
import com.example.roomdatabase.utils.PdfDownloader
import com.example.roomdatabase.viewmodel.BillViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("SetJavaScriptEnabled", "SetTextI18n")
class PreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreviewBinding

    //    private val viewModel: BillViewModel by viewModels()
    @Inject
    lateinit var viewModel: BillViewModel
    private lateinit var webView: WebView
    private lateinit var downloadButton: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        webView = binding.billWebView

        webView.webViewClient = MyWebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()

        val receivedData = intent
        val receivedBillEntity1: BillEntity1? = receivedData.getParcelableExtra("billEntity")

        downloadButton = binding.downloadPDFButton

        if (receivedBillEntity1 != null) {
            Log.d("mybills", receivedBillEntity1.toString())
            val htmlContent = HtmlContent().buildHtmlContent(receivedBillEntity1)
            webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)

            downloadButton.setOnClickListener {
                val pdfDownloader =
                    PdfDownloader() // 'this' is your activity or application context
                pdfDownloader.downloadPdf(webView, "${receivedBillEntity1.billNo}.pdf", this)
            }
        } else {
            Log.d("mybills", "No data")
            (downloadButton as Button).text = "Go to Home"
            downloadButton.setOnClickListener {
                val intent = Intent(this, ShowBillActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

}