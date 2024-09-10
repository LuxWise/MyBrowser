package com.example.mybrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    lateinit var urlInput: EditText
    lateinit var clearUrl: ImageView
    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlInput = findViewById(R.id.inputUri)
        clearUrl = findViewById(R.id.cancel_icon)
        webView = findViewById(R.id.web_view)

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false

        webView.webViewClient = WebViewClient()

        webView.loadUrl("https://www.google.com/")

        urlInput.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {
                val inputText = urlInput.text.toString()

                if (inputText.startsWith("http://") || inputText.startsWith("https://")) {
                    webView.loadUrl(inputText)
                } else if (inputText.isNotEmpty()) {
                    val searchQuery = inputText.replace(" ", "+") // Reemplaza espacios por "+"
                    webView.loadUrl("https://www.google.com/search?q=$searchQuery")
                } else {
                    urlInput.error = "Please enter a valid input"
                }
                true
            } else {
                false
            }
        }


        clearUrl.setOnClickListener {
            urlInput.text.clear()
        }
    }
}
