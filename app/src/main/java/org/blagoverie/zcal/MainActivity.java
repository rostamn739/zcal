package org.blagoverie.zcal;

import android.app.*;
import android.os.*;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.*;
import java.util.Calendar;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    String currentParamBundle;

    public class LauncherListener implements View.OnClickListener
    { @Override
    public void onClick(View view) {
        MainActivity.this.launchDetailJava();
    }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        // TODO: why postcreate?
        super.onPostCreate(savedInstanceState);
        final Button bar = (Button)
                findViewById(
                        R.id.RojMahButton);
        bar.setOnClickListener(
                new LauncherListener());

        DatePicker greg = (DatePicker)
                findViewById(R.id.gregCal);
        java.util.Calendar cal = Calendar.getInstance();
        {
            ZDateCal.ZDate ztoday =
                    ZDateCal.fromGreg(cal.getTime());
            applyParamBundle(bar, ztoday);

        }
        greg.init(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged (DatePicker view,
                                               int year,
                                               int monthOfYear,
                                               int dayOfMonth) {
                        ZDateCal.ZDate zd = ZDateCal.fromGreg(
                                new java.util.GregorianCalendar(
                                        year, monthOfYear, dayOfMonth + 1).getTime());
                        applyParamBundle(bar, zd);
                    }
                });

    }

    void applyParamBundle(
            Button bar, ZDateCal.ZDate zd) {
        bar.setText("roz " + zd.roz() +
                ", mah " + zd.mah() +
                ", sal " + zd.year);
        currentParamBundle = zd.toDescription();
    }

    void launchDetailJava() {
        android.content.Intent it = new Intent(
                this, DetailActivity.class);
        it.putExtra("currentParamBundle", currentParamBundle);
        startActivity(it);
    }
}