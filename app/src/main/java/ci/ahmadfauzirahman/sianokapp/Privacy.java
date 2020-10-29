package ci.ahmadfauzirahman.sianokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Privacy extends AppCompatActivity {
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        web =(WebView)findViewById(R.id.webView);
        web.loadUrl("https://sikaping.deadline.id/web/site/privacy");
    }
}
