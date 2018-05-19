package org.blagoverie.zcal;

import android.app.*;
import android.os.*;
import android.webkit.WebView;
import android.widget.*;
import android.view.View.*;
import android.view.View;
import android.text.Html;


public class DetailActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
    }


    public class Listener implements View.OnClickListener
    { @Override
    public void onClick(View view) {
        DetailActivity.this.finish();
    }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        //why postcreate?
        super.onPostCreate(savedInstanceState);
        WebView descr = (WebView)
                findViewById(R.id.rozHtmlDescr);
        descr.loadData(getIntent()
                .getStringExtra("currentParamBundle"),
                "text/html", null);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//            descr.setText(Html.fromHtml(
//                    getIntent().getStringExtra("currentParamBundle")
//                    , Html.FROM_HTML_MODE_COMPACT));
//        else {
//            descr.setText(Html.fromHtml(
//                    getIntent().getStringExtra("currentParamBundle")));
//        }
        //descr.setText("Description of " +
//getIntent().getStringExtra(
        //"currentParamBundle"));

        Button backbtn = (Button)
                findViewById(R.id.backDetailBtn);
        backbtn.setOnClickListener(new Listener());
    }


}